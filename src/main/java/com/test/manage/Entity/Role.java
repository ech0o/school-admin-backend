package com.test.manage.Entity;

import lombok.Data;

@Data
public class Role {

    private Long id;
    private String role_name;

    public Role(Long id, String role_name) {
        this.id = id;
        this.role_name = role_name;
    }

    public Role() {
    }
}
