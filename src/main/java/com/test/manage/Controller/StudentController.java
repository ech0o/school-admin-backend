package com.test.manage.Controller;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Student;
import com.test.manage.Exception.AppException;
import com.test.manage.Exception.ResourceNotFoundException;
import com.test.manage.Exception.UnauthorizedException;
import com.test.manage.Security.UserPrinciple;
import com.test.manage.Service.StudentService;
import com.test.manage.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public Student listOne(@PathVariable("id") int id) {
        return studentService.findStudentById(id);
    }

    @PostMapping("/")
    public Response saveStudent(@Valid @RequestBody Student student) {

         studentService.saveStudent(student);
         return Response.success("save succeed",student.getId());

    }

    @DeleteMapping("/{id}")
    public Response deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return Response.success("deleted!");

    }

    @PutMapping("/")
    public Response updateStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        studentService.changeStudent(student);
        return Response.success("success");
    }

    @GetMapping("/page")
    public Response getAllStudentByPage(@RequestParam("class") Integer clazzId,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size) {
        return Response.success("success",studentService.listStudentByPage(clazzId, page, size));
    }


}
