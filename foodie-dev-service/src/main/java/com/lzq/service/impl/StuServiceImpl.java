package com.lzq.service.impl;

import com.lzq.mapper.StuMapper;
import com.lzq.pojo.Stu;
import com.lzq.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStu() {
        Stu stu = new Stu();
        stu.setName("jack");
        stu.setAge(19);
        stuMapper.insert(stu);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStu(int id) {
        Stu stu = new Stu();
        stu.setName("tom");
        stu.setAge(500);
        stu.setId(id);
        stuMapper.updateByPrimaryKey(stu);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }
    @Override
    public void saveParent(){
        Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(19);
        stuMapper.insert(stu);

    }
    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void saveChildren(){
        saveChild1();
        int a = 1/0;
        saveChild2();
    }

    private void saveChild2() {
        Stu stu = new Stu();
        stu.setName("child2");
        stu.setAge(22);
        stuMapper.insert(stu);
    }

    private void saveChild1() {
        Stu stu = new Stu();
        stu.setName("child1");
        stu.setAge(11);
        stuMapper.insert(stu);
    }
}
