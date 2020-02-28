package com.test.manage.Service.impl;

import com.test.manage.Entity.RespByPage;
import com.test.manage.Entity.Sclass;
import com.test.manage.Exception.AppException;
import com.test.manage.Exception.ResourceNotFoundException;
import com.test.manage.Mapper.ClazzMapper;
import com.test.manage.Service.SclassService;
import com.test.manage.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SclassServiceImpl implements SclassService {

    private static final Logger logger = LoggerFactory.getLogger(SclassService.class);

    @Autowired(required = false)
    ClazzMapper sclassMapper;

    @Autowired
    UserService userService;

    @Override
    public List<Sclass> getAllSclasses() {
        return sclassMapper.getAllClazz();
    }

    @Override
    public Sclass getSclass(Integer id) {
        try {
            return sclassMapper.getClazz(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void insertSclass(Sclass sclass) {
        try {
            sclassMapper.addClazz(sclass);

        } catch (Exception e) {
            throw new AppException(e.getMessage(),e);
        }
    }

    @Override
    public void updateSclass(Sclass sclass) {
        if (sclass.getId() != null) {
            try{
            sclassMapper.updateClazz(sclass);
            }catch (Exception e){
                throw new AppException(e.getMessage(),e);
            }
        }else{
            throw new ResourceNotFoundException(Sclass.class.getName(),"id",sclass.getId());
        }
    }

    @Override
    public void deleteSclass(Integer id) {
        if(sclassMapper.getClazzById(id)!=null){
            sclassMapper.deleteClazz(id);
        }else{
            throw new ResourceNotFoundException(Sclass.class.getName(),"id",id);
        }
    }

    @Override
    @Transactional
    public RespByPage getSclassByPage(Integer page, Integer size) {
        RespByPage respByPage = new RespByPage();
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Sclass> sclasses = sclassMapper.getClazzByPage(page, size);
        respByPage.setData(sclasses);
        Long total = sclassMapper.getTotal();
        respByPage.setTotal(total);
        return respByPage;
    }
}
