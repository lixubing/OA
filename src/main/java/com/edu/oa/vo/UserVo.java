package com.edu.oa.vo;

import com.edu.oa.mdo.BaseDo;
import com.edu.oa.mdo.Role;

import java.util.List;

public class UserVo {
    /**登录用户id，唯一*/
    private String userId;
    private String username;
    private String password;
    /**性别*/
    private String gender;
    private String age;
    private String tel;
    private String email;
    private String address;
    /**班级*/
    private String clazzNo;
    /**专业*/
    private String majorNo;
    /**学院*/
    private String academyNo;
    /**流程执行的角色ID*/
    private String executorId;
    /**用户权限*/
    private List<Role> roles;

    /**专业名称*/
    private String major;
    /**班级名称*/
    private String className;
    /**学院名称*/
    private String academy;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getExecutorId() {
        return executorId;
    }

    public void setExecutorId(String executorId) {
        this.executorId = executorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClazzNo() {
        return clazzNo;
    }

    public void setClazzNo(String clazzNo) {
        this.clazzNo = clazzNo;
    }

    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    public String getAcademyNo() {
        return academyNo;
    }

    public void setAcademyNo(String academyNo) {
        this.academyNo = academyNo;
    }
}
