package com.huatuo.clinics.cms.db.bean;

public class CmsJgUserRole {
    private Integer id;//主键
    private Integer userId;//用户id
    private Integer roleId;//角色id
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}