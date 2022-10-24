package com.dmc.service;

import com.dmc.dto.StatDTO;

/**
 * Created by Administrator on 2022/10/5 0005.
 */
public interface StatService {

    StatDTO statByTime();

    StatDTO statByKeywords();
}
