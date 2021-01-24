package com.edu.oa.mdo;

import java.util.List;

public class Role extends BaseDo{
    /***/
    private String id;
    /**用户权限*/
    private String authority;
    private String userId;
    private String roleName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    //根据用户id查询角色
    public List<Role> queryRoleByUserId(){
        return (List<Role>) this.getListByParam("Role.queryRoleByUserId", this);
    }

    public String getRoleNameByAuthority() {
        return (String)getObjectByParam("Role.getRoleNameByAuthority", this);
    }
}
