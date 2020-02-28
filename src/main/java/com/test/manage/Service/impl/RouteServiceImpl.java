package com.test.manage.Service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.manage.Entity.Route;
import com.test.manage.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RouteServiceImpl  {

    @Autowired
    RouteRepository routeRepository;


    public List<Route> getAll(){
        return routeRepository.findAll();
    }


    private List<Route> generateRoutes(List<Route> routes, String name) throws IOException {
        List<Route> res = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for(Route route:routes){
            if(hasPermission(route,name)){
                Route tmp = objectMapper.readValue(objectMapper.writeValueAsString(route),Route.class);
                if(tmp.getChildren()!=null){
                    List<Route> children = generateRoutes(tmp.getChildren(),name);
                    tmp.setChildren(children);
                }
                res.add(tmp);
            }
        }
        return res;
    }

    private boolean hasPermission(Route route,String roleName){
        if(route.getMeta()!=null&&route.getMeta().getRoles()!=null){
            List<String> roles = route.getMeta().getRoles();
            for(String role:roles){
                if(Objects.equals(role,roleName)){
                    return true;
                }
            }
        }
        return false;
    }

}
