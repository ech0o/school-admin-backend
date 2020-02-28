package com.test.manage.Entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Sclass {
    private Integer id;
    @NotNull
    private String name;
    private List<Student> students;
}
