package com.test.manage.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Data
@Document("routes")
public class Route implements Serializable {
    @Id
    private String id;
    private String name;
    private String path;
    private Boolean hidden;
    private Boolean matched;
    private String alwaysShow;
    private String redirect;
    private Meta meta;
    private List<Route> children;
}
