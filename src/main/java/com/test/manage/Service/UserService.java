package com.test.manage.Service;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Role;
import com.test.manage.Entity.User;


import java.util.List;

public interface UserService {


    User findUserByName(String name);

    User findUserById(long id);

    List<User> listAll();
    
    void updateUser(User user);

    User getUserRole(String name);

    List<Role> getRoles();

    void save(User user);

    void deleteUser(long id);

    RespByPage getUserByPage(Integer page, Integer size);

    void testPost();

}
