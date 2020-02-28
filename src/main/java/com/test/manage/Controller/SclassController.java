package com.test.manage.Controller;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Sclass;
import com.test.manage.Exception.AppException;
import com.test.manage.Security.UserPrinciple;
import com.test.manage.Service.SclassService;
import com.test.manage.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/grade")
public class SclassController {

    @Autowired
    SclassService sclassService;

    @GetMapping("/all")
    public List<Sclass> getAllClass() {
        return sclassService.getAllSclasses();
    }

    @GetMapping("/{id}")
    public Response getClass(@PathVariable("id") Integer id) {
        Sclass sclass = sclassService.getSclass(id);
        if (sclass != null) {
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (o instanceof UserPrinciple) {
                Integer clazz = ((UserPrinciple) o).getClazz();
                if (Objects.equals(clazz, id) || clazz == -2) {
                    return Response.success("success", sclass);
                } else {
                    return Response.fail("you don't have the permission");
                }
            } else {
                return Response.fail(" you did not log in");
            }
        } else {
            return Response.fail("class don't exist");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public Response createClass(@RequestBody Sclass sclass) {
        sclassService.insertSclass(sclass);
        return Response.success("success",sclass.getId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public Response updateClass(@RequestBody Sclass sclass) {
        sclassService.updateSclass(sclass);
        return Response.success("success");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Response deleteClass(@PathVariable("id") Integer id) {
        sclassService.deleteSclass(id);
        return Response.success("delete successful");
    }

    @GetMapping("/page")
    public RespByPage getClassByPage(@RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size) {
        return sclassService.getSclassByPage(page, size);
    }


}
