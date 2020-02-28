package com.test.manage.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class Student {


    private int id;

    @NotNull
    private String name;
    private int age;
    @NotNull
    private Integer sclass;

    private int gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
