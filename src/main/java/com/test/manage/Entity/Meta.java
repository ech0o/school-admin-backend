package com.test.manage.Entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Meta implements Serializable {
    private String title;
    private List<String> icon;
    private List<String> roles;
}
