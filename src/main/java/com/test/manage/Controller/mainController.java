package com.test.manage.Controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/index")
public class mainController {

    public String index(HttpServletRequest request, HttpServletResponse response){
        return "hello";
    }

}
