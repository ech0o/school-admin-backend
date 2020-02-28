package com.test.manage.Repository;

import com.test.manage.Entity.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RouteRepository extends MongoRepository<Route,String> {
}
