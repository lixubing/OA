package com.edu.oa.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityAuthAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    private MyPasswordEncoder passwordEncoder;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private AuthenticationProvider authenticationProvider;
    @Resource
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //创建DaoAuthenticationProvider对象
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //不要隐藏用户未找到异常
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        //通过重写 configure 方法添加自定义认证
        daoAuthenticationProvider.setUserDetailsService(userService);
        //设置密码加密程序认证认证
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/img/**","/easyui/**","/js/**","/favicon.ico" ).permitAll()
                .antMatchers("/main", "/").hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("name").passwordParameter("password")
                .successHandler(appAuthenticationSuccessHandler)
                .failureUrl("/login?error")
                .and()
                .logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/accessDenied")
                .and().csrf().disable()
                .headers().frameOptions().sameOrigin()
        ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
