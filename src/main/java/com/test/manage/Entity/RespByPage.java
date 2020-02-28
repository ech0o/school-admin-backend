package com.test.manage.Entity;

import lombok.Data;

import java.util.List;

@Data
public class RespByPage {

    private List<?> data;
    private Long total;
}
