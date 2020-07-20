package com.dtreel.sanctuary_shop_manager.service;

import com.dtreel.sanctuary_shop_manager.domain.eneity.MainMenuDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.SubMenuDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.UserDO;
import com.dtreel.sanctuary_shop_manager.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/4
 **/
@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public List<SubMenuDO> listSubMenuWithRole() {
        return menuMapper.listSubMenuWithRole();
    }

    public List<MainMenuDO> listMainMenuByUserId() {
        //SecurityContextHolder.getContext().getAuthentication().getPrincipal()拿到当前登录的UserDo对象
        return menuMapper.listMainMenuByUserId(((UserDO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
