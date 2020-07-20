package com.dtreel.sanctuary_shop_manager.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author DtreeL
 * 判断当前用户是否拥有对应角色
 * 一个请求走完FilterInvocationSecurityMetadataSource中的getAttributes方法后，就会来到AccessDecisionManager类中进行角色信息比对
 */
@Component
public class UserAccessDecisionManager implements AccessDecisionManager {
    @Override
    //在decide方法中判断当前用户是否具有访问的url所具备的角色
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributeCollection) throws AccessDeniedException, InsufficientAuthenticationException {
        //Authentication包含当前用户的信息，Collection<ConfigAttribute>就是FilterInvocationSecurityMetadataSource中的getAttributes方法的返回值，存储着需要的角色信息
        //遍历，然后获取需要的角色
        for (ConfigAttribute configAttribute : configAttributeCollection) {
            String needRole = configAttribute.getAttribute();

            //如果登录即可访问,authentication是UsernamePasswordAuthenticationToken的实例，说明当前用户已登录
            if ("LOGIN_ROLE".equals(needRole) && authentication instanceof UsernamePasswordAuthenticationToken) {
                return;
            } else {
                //如果是匿名用户，说明没有登录，抛出AccessDeniedException权限不足异常
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录，请登录");
                }
            }
            //获取到当前用户拥有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                //如果拥有其中需要的一个角色直接return
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        //不具备任何角色，直接抛出AccessDeniedException异常
        throw new AccessDeniedException("权限不足，请联系店长！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    //返回的类是否支持校验
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
