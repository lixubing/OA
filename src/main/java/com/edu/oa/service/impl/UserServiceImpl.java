package com.edu.oa.service.impl;

import com.edu.oa.mdo.ClazzDo;
import com.edu.oa.mdo.CourseDo;
import com.edu.oa.mdo.Role;
import com.edu.oa.mdo.User;
import com.edu.oa.service.IUserService;
import com.edu.oa.util.SwapAreaUtils;
import com.edu.oa.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
public class UserServiceImpl implements UserDetailsService, IUserService {

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

    @Override
    public int updatePersonalInformation(UserVo vo) {
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        user.updatePersonalInformation();
        return 0;
    }

    /**
     * 完善用户信息
     * @return
     */
    public UserVo completeUser(User user){
        UserVo outVo = new UserVo();
        BeanUtils.copyProperties(user, outVo);
        List<Role> roles = user.getRoles();
        String courseName = "";
        String academyName = "";
        for (Role role : roles) {
            if (role.getAuthority().equals("ROLE_STUDENT")){//如果是学生
                ClazzDo clazzDo = new ClazzDo();
                clazzDo.setClassNo(user.getClazzNo());
                clazzDo = clazzDo.findClazzById();
                outVo.setAcademy(clazzDo.getAcademyName());
                outVo.setMajor(clazzDo.getMajorName());
                outVo.setClassName(clazzDo.getClassName());
            } else if (role.getAuthority().equals("ROLE_TEACHER")){//如果是老师
                //获取课程名称
                CourseDo courseDo = new CourseDo();
                courseDo.setTeacherNo(user.getUserId());
                List<CourseDo> courses = courseDo.queryCourseByTescherNo();
                StringBuilder sb = new StringBuilder();
                for (CourseDo course : courses) {
                    sb.append(course.getCourseName()).append(",");
                }
                if (sb.length() > 0){
                    sb.deleteCharAt(sb.length() - 1);
                    courseName = sb.toString();
                }
                outVo.setCourseName(courseName);
                academyName = getAcademyNameByAcademyNo(user.getAcademyNo());
                outVo.setAcademy(academyName);
            }else if (role.getAuthority().equals("ROLE_CHAIRMAN")) {
                //主任，不做任何操作
            }else{ //如果是辅导员/院长
                academyName = getAcademyNameByAcademyNo(user.getAcademyNo());
                outVo.setAcademy(academyName);
            }

        }

        LOG.info("用户信息界面的用户id = " + user.getUserId());
        return outVo;
    }
    private String getAcademyNameByAcademyNo(String academyNo){
        ClazzDo clazzDo = new ClazzDo();
        clazzDo.setAcademyNo(academyNo);
        clazzDo = clazzDo.queryAcademyNameByAcademyNo();
        LOG.info("学院id=" + academyNo + "|学院名称=" + clazzDo.getAcademyName());
        return clazzDo.getAcademyName();
    }
}
