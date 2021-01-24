package com.edu.oa.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    //springsecurity 通过 RedirectStrategy 对象负责所有重定向业务
    private RedirectStrategy strategy = new DefaultRedirectStrategy();
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = determineTargetUrl(authentication);
        strategy.sendRedirect(request, response, redirectUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.toString());
        }
        if (roles.contains("ROLE_STUDENT")){
            //学生和班长
            return "/main";
        }else if (roles.contains("ROLE_COUNSELOR")  //辅导员，
                || roles.contains("ROLE_DEAN")  //院长
                || roles.contains("ROLE_CHAIRMAN")  //主任
                || roles.contains("ROLE_TEACHER")){ //老师
            return "/teacherMain";
        } else {
            //不存在规定的角色
            return "/accessDenied";
        }
    }
}
