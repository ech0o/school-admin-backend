package com.test.manage.Service.impl;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Role;
import com.test.manage.Entity.User;
import com.test.manage.Exception.AppException;
import com.test.manage.Exception.ResourceNotFoundException;
import com.test.manage.Mapper.UserMapper;
import com.test.manage.Service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserByName(String name) {
        User user = userMapper.findUserByName(name);
        return user;
    }

    @Override
    public List<User> listAll() {
        List<User> users = userMapper.listAll();
        return users;
    }

    @Override
    @Transactional
    public RespByPage getUserByPage(Integer page, Integer size) {
        RespByPage respByPage = new RespByPage();
        if (page > 0 && size > 0) {
            page = (page - 1) * size;
        }
        List<User> users = userMapper.getUserByPage(page, size);
        respByPage.setData(users);
        respByPage.setTotal(userMapper.getTotal());
        return respByPage;
    }

    @Override
    public void updateUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        try {
            userMapper.updateUser(user);
            if (user.getRoles() != null) {
                userMapper.deleteUserRole(user.getId());
                userMapper.setUserRole(user);
            }
        } catch (Exception e) {
            throw new AppException(e.getMessage(),e);
        }

    }

    @Override
    public User getUserRole(String name) {

        return userMapper.getUserRole(name);

    }

    @Override
    public void save(User user) {
        if (user.getRoles() == null) {
            List<Role> roles = new ArrayList<>();
            roles.add(new Role(2L, "ROLE_USER"));
            user.setRoles(roles);
        }
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }else{
            throw new IllegalArgumentException("missing user's password");
        }
        try{
            userMapper.saveUser(user);
            userMapper.setUserRole(user);
        }catch (Exception e){
            throw new AppException(e.getLocalizedMessage(),e);
        }
    }

    @Override
    public void deleteUser(long id) {
        if (userMapper.findUserById(id) == null) {
            throw new ResourceNotFoundException(User.class.getName(), "user_id", id);
        }
        userMapper.deleteUserRole(id);
        userMapper.deleteUser(id);
    }

    @Override
    public List<Role> getRoles() {
        return userMapper.getRoles();
    }

    @Override
    @PostConstruct
    public void testPost(){
        userMapper.findUserById(121);
    }


}
