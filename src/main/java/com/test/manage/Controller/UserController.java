package com.test.manage.Controller;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Role;
import com.test.manage.Entity.User;
import com.test.manage.Exception.AppException;
import com.test.manage.Security.JwtTokenProvider;
import com.test.manage.Security.UserPrinciple;
import com.test.manage.Service.UserService;
import com.test.manage.Util.Response;
import com.test.manage.Util.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    //deprecated
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam("username") String username,
//                                        @RequestParam("password") String password, HttpSession httpSession) {
//
//        User user = userService.findUserByName(username);
//        if (user == null) {
//            return ResponseEntity.badRequest().body("user dose not exist");
//        }
//        if (!Objects.equals(password, user.getPassword())) {
//            return ResponseEntity.badRequest().body("wrong password");
//        } else {
//            httpSession.setAttribute("user", user);
//            return ResponseEntity.ok("3");
//        }
//    }

    @GetMapping("/page")
    public RespByPage getUserByPage(@RequestParam("page") Integer page,
                                    @RequestParam("size") Integer size) {
        return userService.getUserByPage(page, size);
    }


//    @GetMapping("/encode")
//    public Response passwordEncode() {
//        List<User> users = userService.listAll();
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        for (User user : users) {
//            String password = user.getPassword();
//            String hashed = bCryptPasswordEncoder.encode(password);
//            user.setPassword(hashed);
//            userService.updateUser(user);
//        }
//        return Response.success("ok");
//    }


    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return userService.getRoles();
    }

//    @GetMapping("/{id}")
//    public Response userIdTest(@PathVariable("id") long id) {
//        User user = userService.findUserById(id);
//        return Response.success("ok", user);
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public Response save(@RequestBody User user) {
        userService.save(user);
        return Response.success("ok,user id:",user.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    public Response updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Response.success("change succeed");

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return Response.success("deleted!");
    }

    @PostMapping("/info")
    public UserProfile getUserInfo(@RequestParam("token") String token) {
        Long id = tokenProvider.getUserIdFromJwt(token);
        User user = userService.findUserById(id);
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer clazzId = ((UserPrinciple) o).getClazz();
        return new UserProfile(user.getName(), user.getRoles(), clazzId);
    }
}




    

