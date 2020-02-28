package com.test.manage.Controller;

import com.test.manage.Entity.RoleList;
import com.test.manage.Service.RoleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleListController {
    @Autowired
    RoleListService roleListService;

    @GetMapping("/all")
    public List<RoleList> getAllRoles(){
        return roleListService.findAll();
    }

}
