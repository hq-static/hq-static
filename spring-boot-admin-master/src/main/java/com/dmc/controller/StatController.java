package com.dmc.controller;

import com.dmc.dto.StatDTO;
import com.dmc.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2022/10/6 0006.
 */
@RestController
@RequestMapping("/stat")
public class StatController {
    @Autowired
    private StatService statService;

    @RequestMapping("/bytime")
    public StatDTO statByTime() {
        return statService.statByTime();
    }

    @RequestMapping("/bykeywords")
    public StatDTO statByKeywords() {
        return statService.statByKeywords();
    }
}
