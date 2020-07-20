package com.dtreel.sanctuary_shop_manager.config;

import com.dtreel.sanctuary_shop_manager.domain.eneity.RoleDO;
import com.dtreel.sanctuary_shop_manager.domain.eneity.SubMenuDO;
import com.dtreel.sanctuary_shop_manager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author DtreeL
 * 动态配置权限，根据访问url，分析出访问所需要的角色
 */
@Component
public class RoleFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    //覆写的getAttributes方法确定一个请求需要哪些角色，返回值Collection<ConfigAttribute>表示当前请求url所需的角色
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //从FilterInvocation对象的getRequestUrl获得当前访问的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        //从数据库中获取所有的角色对应资源的信息，可以放在Redis或者其他数据库红
        List<SubMenuDO> submenus = menuService.listSubMenuWithRole();
        if (submenus == null){
            //没匹配上，默认登录即可访问，直接返回字符串LOGIN_ROLE
            return SecurityConfig.createList("LOGIN_ROLE");
        }
        //需要返回的是SecurityConfig.createList(strings)，参数为字符串数组，所以把role对象列表的名字转存到一个字符串数组里
        for (SubMenuDO submenu : submenus) {
            //使用AntPathMatcher进行ant风格的匹配，如果匹配到了则取出该资源所需要的角色对象集合
            if (antPathMatcher.match(submenu.getUrl(), requestUrl)){
                List<RoleDO> roles = submenu.getRoles();
                String [] strings = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++){
                    //只把角色里的名字装进来，id不需要
                    strings[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(strings);
            }
        }
        return SecurityConfig.createList("LOGIN_ROLE");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    //类对象是否支持校验
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
