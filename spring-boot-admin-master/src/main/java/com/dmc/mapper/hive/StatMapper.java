package com.dmc.mapper.hive;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dmc.model.StatVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2022/10/5 0005.
 */
@Mapper
@DS("hive")
public interface StatMapper {

    /**
     * 按时间段统计访问次数
     * @return 返回StatVO组成的数组，statVO由统计字段和数字组成
     */
    List<StatVO> statByTime();

    /**
     * 按关键字统计访问次数（排序取top10）
     * @return 返回StatVO组成的数组，statVO由统计字段和数字组成
     */
    List<StatVO> statByKeywords();
}
