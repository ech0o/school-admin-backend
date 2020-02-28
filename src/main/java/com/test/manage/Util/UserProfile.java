package com.test.manage.Util;

import com.test.manage.Entity.Role;

import java.util.List;

public class UserProfile {

    private String name;

    private List<Role> roles;

    private Integer clazzId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    public UserProfile(String name, List<Role> roles, Integer clazzId) {
        this.name = name;
        this.roles = roles;
        this.clazzId = clazzId;
    }
}
