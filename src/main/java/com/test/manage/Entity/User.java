package com.test.manage.Entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private Long id;
    private String name;
    private String password;
    private List<Role> roles;
    private Integer clazz;
}
