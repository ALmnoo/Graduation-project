package com.dtreel.sanctuary_shop_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/4
 **/
@RestController
@RequestMapping("/home/userManager/api")
public class Test {
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
