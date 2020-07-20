package com.dtreel.sanctuary_shop_manager.service;

import com.dtreel.sanctuary_shop_manager.domain.eneity.UserDO;
import com.dtreel.sanctuary_shop_manager.mapper.RoleMapper;
import com.dtreel.sanctuary_shop_manager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author DtreeL
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = userMapper.getUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoles(roleMapper.listRolesById(user.getId()));
        return user;
    }
}
