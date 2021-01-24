package com.edu.oa.service.impl;

import com.edu.oa.mdo.Role;
import com.edu.oa.mdo.User;
import com.edu.oa.util.SwapAreaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要实现UserDetailsService接口
 * 因为在springsecurity 中配置的相关参数需要是UserDetailsService 类型的数据
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * 重写UserDetailsService 接口中的loadUserByUsername 方法，<br/>
     * 通过该方法查询对应的用户
     * @param username 传进来的是用户在页面登录的时候输入的用户名，对应到user中是userId字段
     * @return UserDetails 是springsecurity 的一个核心接口<br/>
     *          其中定义了一些可以获取用户名、密码、权限等与认证信息相关的方法
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUserId(username);
        user = user.queryUserByUserId();
        if (user == null)
            throw  new RuntimeException("用户名不存在");
        //创建list集合来存储用户权限,GrantedAuthority对象代表赋予当前用户的权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        //获取当前用户的权限集合
        List<Role> roles = user.getRoles();
        LOG.info("角色个数=" + roles.size());
        for (Role role : roles) {
            //将role的authority属性保存为用户的认证权限
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
            LOG.info("角色权限authority=" + role.getAuthority());
        }
        //此处返回的是springsecurity内部的user类
        //参数 用户姓名，密码，权限
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }
}
