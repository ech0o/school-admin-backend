package com.test.manage.Mapper;

import com.test.manage.Entity.Role;
import com.test.manage.Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper  {

    User findUserByName(@Param(value="name") String name);

    User findUserById(long id);

    List<User> listAll();
    
    void updateUser(User user);

    User getUserRole(String name);

    void saveUser(User user);

    void setUserRole(User user);

    void deleteUser(long id);

    void deleteUserRole(long id);

    List<Role> getRoles();

    List<User> getUserByPage(Integer page,Integer size);

    Long getTotal();


}
