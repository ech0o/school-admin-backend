package com.test.manage.Service.impl;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Student;
import com.test.manage.Exception.AppException;
import com.test.manage.Exception.ResourceNotFoundException;
import com.test.manage.Exception.UnauthorizedException;
import com.test.manage.Mapper.StudentMapper;
import com.test.manage.Security.UserPrinciple;
import com.test.manage.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private StudentMapper studentMapper;


    @Override
    public Student findStudentById(int id) {
        return studentMapper.findStudentById(id);
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return studentMapper.findStudentByName(name);
    }

    @Override
    public void changeStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
            LocalDate birth = student.getBirthDate();
            LocalDate now = LocalDate.now();
            student.setAge(now.getYear() - birth.getYear());
        } else {
            throw new ResourceNotFoundException(Student.class.getName(), "id", student.getId());
        }
        try {
            studentMapper.updateStudent(student);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteStudent(int id) {
        if (findStudentById(id) != null) {
            studentMapper.deleteStudent(id);
        } else {
            throw new ResourceNotFoundException(Student.class.getName(), "id", id);
        }
    }

    @Override
    public void saveStudent(Student student) {
        if (student != null) {
            LocalDate birth = student.getBirthDate();
            LocalDate now = LocalDate.now();
            if (birth != null) {
                student.setAge(now.getYear() - birth.getYear());
            }
        }
        try {
            studentMapper.insertStudent(student);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }


    }

    @Override
    public List<Student> listAll() {
        return studentMapper.findAll();
    }

    @Override
    @Transactional
    public RespByPage listStudentByPage(Integer clazzId, Integer page, Integer size) {
        if (clazzId != null && page != null && size != null) {
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (o instanceof UserPrinciple) {
                Integer clazz = ((UserPrinciple) o).getClazz();
                if (Objects.equals(clazz, clazzId) || clazz == -2) {
                    RespByPage pageEntity = new RespByPage();
                    page = (page - 1) * size;
                    List<Student> students = studentMapper.getAllStudentByPage(clazzId, page, size);
                    pageEntity.setData(students);
                    Long total = studentMapper.getTotal(clazzId);
                    pageEntity.setTotal(total);
                    return pageEntity;
                } else {
                    throw new UnauthorizedException("you are not allowed to access the resource");
                }
            } else {
                throw new UnauthorizedException("please log in");
            }

        } else {
            throw new IllegalArgumentException("missing parameters");
        }
    }

}
