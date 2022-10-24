package com.dmc.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2022/10/5 0005.
 */
@Data
public class StatDTO {
    private List<String> axis;

    private List<Integer> data;
}
