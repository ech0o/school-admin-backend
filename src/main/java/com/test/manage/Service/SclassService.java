package com.test.manage.Service;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Sclass;

import java.util.List;

public interface SclassService {
    List<Sclass> getAllSclasses();

    Sclass getSclass(Integer id);

    void updateSclass(Sclass sclass);

    void insertSclass(Sclass sclass);

    void deleteSclass(Integer id);

    RespByPage getSclassByPage(Integer page, Integer size);
}
