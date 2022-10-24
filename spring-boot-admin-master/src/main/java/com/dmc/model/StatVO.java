package com.dmc.model;

import lombok.Data;

import java.util.List;

/**
 * 用于返回给echarts的统计结果
 * Created by Administrator on 2022/10/5 0005.
 */
@Data
public class StatVO {
    // 统计名称
    private String statName;
    // 统计结果
    private int cnt;
}
