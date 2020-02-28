package com.test.manage.Controller;

import com.test.manage.Entity.Route;
import com.test.manage.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;

    @GetMapping("/all")
    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }
}
