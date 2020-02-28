package com.test.manage.Mapper;

import com.test.manage.Entity.Sclass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Sclass> getAllClazz();

    Sclass getClazz(Integer id);

    void updateClazz(Sclass sclass);

    void addClazz(Sclass sclass);

    void deleteClazz(Integer id);

    Sclass getClazzById(Integer id);

    List<Sclass> getClazzByPage(Integer page, Integer size);

    Long getTotal();

}
