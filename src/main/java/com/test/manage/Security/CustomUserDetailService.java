package com.test.manage.Security;

import com.test.manage.Entity.User;
import com.test.manage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getUserRole(name);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return UserPrinciple.create(user);

    }

    @Transactional
    public UserDetails loadUserById(long id){
        User user = userService.findUserById(id);
        return UserPrinciple.create(user);
    }


}
