package com.test.manage.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Data
@Document("roles")
public class RoleList {

    @Id
    private String  id;

    @Field("role_name")
    private String roleName;
    private String comment;
    private List<Route> routes;
}
