package com.forur2bhappy.rainbowfart.controller;

import com.forur2bhappy.rainbowfart.model.RainBowFart;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理台controller
 * Created by dyw on 2017/11/28.
 */
@RestController
@RequestMapping("/manager")
public class RainBowFartController {
    private static final Logger logger = LoggerFactory.getLogger(RainBowFartController.class);

    @ApiOperation(value = "测试", notes = "测试")
    @GetMapping("test")
    public RainBowFart test(String id){
        RainBowFart rainBowFart = new RainBowFart();
        rainBowFart.setId(111L);
//        return "success";
        return rainBowFart;
    }


}
