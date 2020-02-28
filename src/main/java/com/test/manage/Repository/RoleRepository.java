package com.test.manage.Repository;

import com.test.manage.Entity.RoleList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<RoleList,String> {
    RoleList findByRoleName(String roleName);
}
