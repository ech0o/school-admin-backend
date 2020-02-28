package com.test.manage.Mapper;

import com.test.manage.Entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {


    List<Student> findStudentByName(String name);

    Student findStudentById(int id);

    void updateStudent(Student student);

    void insertStudent(Student student);

    void deleteStudent(int id);

    List<Student> findAll();

    List<Student> getAllStudentByPage(Integer clazzId, Integer page, Integer size);

    Long getTotal(Integer clazz);


}
