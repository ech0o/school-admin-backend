package com.test.manage.Service;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Student;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentService {

    List<Student> findStudentByName(String name);

    Student findStudentById(int id);

    void saveStudent(Student student);

    void deleteStudent(int id);

    void changeStudent(Student student);

    List<Student> listAll();

    RespByPage listStudentByPage(Integer clazzId,Integer page, Integer size);


}
