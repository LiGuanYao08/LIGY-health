package com.LIGY.pojo;

public class Role_Perssion {
    private Integer roleId;
    private Integer perId;

    @Override
    public String toString() {
        return "Role_Perssion{" +
                "roleId=" + roleId +
                ", perId=" + perId +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public Role_Perssion() {
    }

    public Role_Perssion(Integer roleId, Integer perId) {
        this.roleId = roleId;
        this.perId = perId;
    }
}
