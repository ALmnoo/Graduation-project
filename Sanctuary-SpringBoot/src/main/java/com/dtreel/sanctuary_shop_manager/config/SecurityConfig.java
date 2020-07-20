package com.dtreel.sanctuary_shop_manager.config;

import com.dtreel.sanctuary_shop_manager.domain.ResponseVO;
import com.dtreel.sanctuary_shop_manager.domain.StatusCode;
import com.dtreel.sanctuary_shop_manager.domain.eneity.UserDO;
import com.dtreel.sanctuary_shop_manager.mapper.UserMapper;
import com.dtreel.sanctuary_shop_manager.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


/**
 * @Description TODO
 * @Author DtreeL
 * @Date 2020/5/2
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleFilterInvocationSecurityMetadataSource roleFilterInvocationSecurityMetadataSource;

    @Autowired
    private UserAccessDecisionManager userAccessDecisionManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //装载自定义好的动态权限类
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(roleFilterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(userAccessDecisionManager);
                        return object;
                    }
                })
                .and()

                //登录表单配置
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")

                //登录处理api
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")

                //登录成功处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        //设置响应内容为json格式，编码为utf-8
                        response.setContentType("application/json;charset=utf-8");

                        PrintWriter outWriter = response.getWriter();

                        //authentication存储着用户的信息,getPrincipal方法获取当前登录用户信息
                        UserDO user = (UserDO) authentication.getPrincipal();

                        //防止返回的json对象中携带用户密码泄露，设置密码为null
                        user.setPassword("null");
                        ResponseVO responseVO;

                        UserDO dataUserDO = userMapper.getUserByUsername(user.getUsername());

                        long userAllowTime = dataUserDO.getAllowTime() == null? 0 : dataUserDO.getAllowTime().getTime();
                        Date currentTime = new Date();

                        //如果允许登录
                        if (userAllowTime < currentTime.getTime()) {
                            responseVO = ResponseVO.success(StatusCode.SUCCESS,"登录成功！",user);

                            //清零错误次数
                            dataUserDO.setErrorNum(0);
                            //设置允许登录时间
                            dataUserDO.setAllowTime(new Date());
                            userMapper.updateUser(dataUserDO);
                        } else {
                            long requireSecond = (userAllowTime - currentTime.getTime()) / 1000;
                            responseVO = ResponseVO.error(StatusCode.LOGIN_FAILED,"还有" + requireSecond + "秒才能进行下一次登录！");
                        }

                        String string = new ObjectMapper().writeValueAsString(responseVO);
                        outWriter.write(string);
                        outWriter.flush();
                        outWriter.close();
                    }
                })
                //登录失败处理
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter outWriter = response.getWriter();

                        ResponseVO responseVO = ResponseVO.error(StatusCode.PASSWORD_ERROR,"登录失败!");

                        String username = request.getParameter("username");
                        UserDO dataUserDO = userMapper.getUserByUsername(username);

                        //初始化错误次数
                        int errorNum = dataUserDO.getErrorNum() == null ? 0 : dataUserDO.getErrorNum();
                        //初始化登录允许时间
                        long userAllowTime = dataUserDO.getAllowTime() == null? 0 : dataUserDO.getAllowTime().getTime();
                        Date currentTime = new Date();

                        //如果可以登录
                        if (userAllowTime < currentTime.getTime()) {
                            //登录错误次数小于2时
                            if (errorNum < 2) {
                                dataUserDO.setErrorNum(errorNum + 1);
                                int result = userMapper.updateUser(dataUserDO);
                                if (result == 1) {
                                    responseVO.setMessage("用户名或密码输入错误，请重新输入！");
                                } else {
                                    responseVO.setStatusCode(StatusCode.DATABASE_ERROR);
                                    responseVO.setMessage("数据库异常！");
                                }
                            } else {
                                //已经错了三次，清空错误次数，设置限定登录时间
                                dataUserDO.setErrorNum(0);
                                Date afterLockAllowTime = new Date(System.currentTimeMillis() + 10000);
                                dataUserDO.setAllowTime(afterLockAllowTime);

                                int result = userMapper.updateUser(dataUserDO);
                                if (result == 1) {
                                    responseVO.setStatusCode(StatusCode.LOGIN_FAILED);
                                    responseVO.setMessage("连续三次输入密码错误，账号被锁定10秒！");
                                } else {
                                    responseVO.setStatusCode(StatusCode.DATABASE_ERROR);
                                    responseVO.setMessage("数据库异常！");
                                }
                            }
                        }else {
                            long requireSecond = (userAllowTime - currentTime.getTime()) / 1000;
                            responseVO = ResponseVO.error(StatusCode.LOGIN_FAILED,"还有" + requireSecond + "秒才能进行下一次登录！");
                        }

                        String string = new ObjectMapper().writeValueAsString(responseVO);
                        outWriter.write(string);
                        outWriter.flush();
                        outWriter.close();
                    }
                })
                .permitAll()
                .and()

                //注销配置
                .logout()
                .logoutUrl("/logout")

                //清空认证信息，使Session失效
                .clearAuthentication(true)
                .invalidateHttpSession(true)

                //注销成功处理
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter outWriter = response.getWriter();
                        ResponseVO responseVO = ResponseVO.success(StatusCode.SUCCESS,"注销成功");
                        outWriter.write(new ObjectMapper().writeValueAsString(responseVO));
                        outWriter.flush();
                        outWriter.close();
                    }
                })
                .permitAll()
                .and()

                //关闭CSRF(Cross—Site Request Forgery)跨站点请求伪造，需要用postman工具测试请求是否正常，所以在这里先关闭
                .csrf().disable()

                //没有认证访问api时，在这里处理结果，不重定向，给予一个提示
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(401);
                PrintWriter out = response.getWriter();//获得输出字符流
                ResponseVO responseVO = ResponseVO.error(StatusCode.NOT_OPERATE_AUTH,"访问失败！");
                if (e instanceof InsufficientAuthenticationException) {
                    responseVO.setMessage("权限不足，请联系店长！");
                }
                out.write(new ObjectMapper().writeValueAsString(responseVO));
                out.flush();
                out.close();
            }
        });
    }
}
