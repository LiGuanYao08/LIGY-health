package com.LIGY.pojo;

public class User_Role {
    private Integer userId;
    private Integer roleId;

    @Override
    public String toString() {
        return "User_Role{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
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

    public User_Role() {
    }

    public User_Role(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
