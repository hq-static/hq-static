package com.dmc.service.impl;

import com.dmc.dto.StatDTO;
import com.dmc.mapper.hive.StatMapper;
import com.dmc.service.StatService;
import com.dmc.model.StatVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2022/10/5 0005.
 */
@Service
public class StatServiceImpl implements StatService{
    private Logger logger = LoggerFactory.getLogger(StatServiceImpl.class);
    @Autowired
    private StatMapper statMapper;

    @Override
    public StatDTO statByTime() {
        List<StatVO> list = statMapper.statByTime();
        logger.info("get stat result by time: " + list.toString());
        StatDTO result = null;
        if (list.size() > 0)
            result = assembleStatDTO(list,false);
        return result;
    }

    /**
     * 根据值对象列表生成DTO到前台，格式支持echarts显示
     * @param list 值对象列表
     * @param reverse 是否将list按倒序插入DTO中，true为倒序。若要echarts的显示顺序与数据顺序一致，若需要横向倒序显示，则需要倒序插入数据
     * @return 转换后的dto对象，axis为坐标轴坐标名称，data为对应值
     */
    private StatDTO assembleStatDTO(List<StatVO> list, boolean reverse)
    {
        StatDTO stat = new StatDTO();
        List<String> axis = new LinkedList<String>();
        List<Integer> vals = new LinkedList<Integer>();
        for (StatVO vo : list) {
            if (reverse) {
                // 图表需要倒序排列时，数据需要正序排列
                axis.add(0,vo.getStatName());
                vals.add(0,vo.getCnt());
            }else {
                axis.add(vo.getStatName());
                vals.add(vo.getCnt());
            }
        }
        stat.setAxis(axis);
        stat.setData(vals);
        return stat;
    }

    @Override
    public StatDTO statByKeywords() {
        List<StatVO> list = statMapper.statByKeywords();
        logger.info("get stat result by keywords: " + list.toString());
        StatDTO result = null;
        if (list.size() > 0)
            result = assembleStatDTO(list,true);
        return result;
    }
}
