package com.dtreel.sanctuary_shop_manager.controller;


import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.StatusCode;
import com.dtreel.sanctuary_shop_manager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DtreeL
 */
@RestController
public class MenuConfigController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/home/api/menuConfig")
    public ResponseVO listMenuByUserId(){
        if (menuService.listMainMenuByUserId()!= null){
            return ResponseVO.success(StatusCode.SUCCESS,"查询成功!",menuService.listMainMenuByUserId());
        }
        return ResponseVO.error(StatusCode.ERROR,"查询失败!");
    }
}
