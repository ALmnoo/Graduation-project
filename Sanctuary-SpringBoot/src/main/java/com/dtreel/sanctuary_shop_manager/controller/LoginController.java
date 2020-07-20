package com.dtreel.sanctuary_shop_manager.controller;

import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DtreeL
 */
@RestController
public class LoginController {
    @GetMapping("/login")
    public ResponseVO login(){
        return ResponseVO.error(StatusCode.NOT_OPERATE_AUTH,"尚未登录，请登录!");
    }

}
