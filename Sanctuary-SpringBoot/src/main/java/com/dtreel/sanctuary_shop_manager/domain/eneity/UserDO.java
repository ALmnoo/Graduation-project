package com.dtreel.sanctuary_shop_manager.domain.eneity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author DtreeL
 * 基于数据库认证的用户类需要实现UserDetail接口，实现其方法
 */
@Data
@NoArgsConstructor
public class UserDO implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private List<RoleDO> roles;
    private String owner;
    private String gender;
    private String note;
    private String avatar;
    private Integer errorNum;
    private Date allowTime;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //构建SimpleGrantedAuthority集合，用角色名构建SimpleGrantedAuthority对象并添加到集合里
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (RoleDO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        //返回具有角色信息的集合
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
