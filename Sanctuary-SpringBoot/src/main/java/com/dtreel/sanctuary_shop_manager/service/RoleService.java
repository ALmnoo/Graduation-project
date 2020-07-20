package com.dtreel.sanctuary_shop_manager.service;

import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.StatusCode;
import com.dtreel.sanctuary_shop_manager.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/4
 **/
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public ResponseVO listAllRole() {
        if (roleMapper.listAllRole() != null){
            return ResponseVO.success(StatusCode.SUCCESS,"查询所有职位成功！", roleMapper.listAllRole());
        }
        return ResponseVO.error(StatusCode.ERROR,"查询所有职位失败！");
    }
}
