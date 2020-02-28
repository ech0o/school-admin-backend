package com.test.manage.Service;

import com.test.manage.Entity.RoleList;

import java.io.IOException;
import java.util.List;

public interface RoleListService {
    void saveRoleList(RoleList roleList);

    List<RoleList> findAll();

    RoleList findByName(String name);

    void setRoleRoute()throws IOException;
}
