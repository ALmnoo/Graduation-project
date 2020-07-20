package com.dtreel.sanctuary_shop_manager.service;

import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.StatusCode;
import com.dtreel.sanctuary_shop_manager.domain.eneity.UserDO;
import com.dtreel.sanctuary_shop_manager.mapper.UserAndRoleMapper;
import com.dtreel.sanctuary_shop_manager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/4
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAndRoleMapper userAndRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //查询用户
    public ResponseVO listUser(String keyword) {
        if (userMapper.listUser(keyword) != null) {
            return ResponseVO.success(StatusCode.SUCCESS,"查询用户成功", userMapper.listUser(keyword));
        }
        return ResponseVO.error(StatusCode.ERROR,"查询用户失败，无此用户");
    }

    @Transactional
    public ResponseVO insertUser(UserDO user) {
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
        //添加操作
        int userResult = userMapper.insertUser(user);
        int userId = user.getId();
        if (userResult == 1 && userAndRoleMapper.insertUserRole(userId, user.getRoles()) == user.getRoles().size()) {
            return ResponseVO.success(StatusCode.SUCCESS,"添加用户成功！");
        }
        return ResponseVO.error(StatusCode.ERROR,"添加用户失败！");
    }

    @Transactional
    public ResponseVO updateUser(UserDO user, Integer[] addRoleIds, Integer[] deleteRoleIds) {
        int userRoleAddResult = 0;
        int userRoleDeleteResult = 0;
        //如果有需要增加的职位，执行增加操作
        if (addRoleIds != null && addRoleIds.length != 0) {
            userRoleAddResult = userAndRoleMapper.insertUserRoleByUserIdAndRoleId(user.getId(), addRoleIds);
        }
        //如果有需要删除的职位，执行删除操作
        if (deleteRoleIds != null && deleteRoleIds.length != 0) {
            userRoleDeleteResult = userAndRoleMapper.deleteRoleByUserIdAndRoleId(user.getId(), deleteRoleIds);
        }
        if (userMapper.updateUser(user) == 1 || userRoleAddResult == addRoleIds.length && userRoleDeleteResult == deleteRoleIds.length) {
            return ResponseVO.success(StatusCode.SUCCESS,"修改用户信息成功！");
        }
        return ResponseVO.error(StatusCode.ERROR,"修改用户信息失败！");
    }

    //删除用户
    @Transactional
    public ResponseVO deleteUser(Integer id) {
        //先删除用户的所有职位
        int userRoleResult = userAndRoleMapper.deleteRoleByUserId(id);
        //再删除用户
        if (userMapper.deleteUser(id) == 1 && userRoleResult > 0) {
            return ResponseVO.success(StatusCode.SUCCESS,"删除用户成功！");
        }
        return ResponseVO.error(StatusCode.ERROR,"删除用户失败！");
    }
}
