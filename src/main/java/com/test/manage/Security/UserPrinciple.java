package com.test.manage.Security;

import com.test.manage.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {

    private long id;
    private String username;
    private String password;
    private Integer clazz;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(long id, String name, String password, Integer clazz,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.clazz = clazz;
        this.authorities = authorities;
    }

    public static UserPrinciple create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRole_name())).collect(Collectors.toList());
        return new UserPrinciple(user.getId(), user.getName(), user.getPassword(), user.getClazz(), authorities);
    }

    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Integer getClazz() {
        return clazz;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrinciple that = (UserPrinciple) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
