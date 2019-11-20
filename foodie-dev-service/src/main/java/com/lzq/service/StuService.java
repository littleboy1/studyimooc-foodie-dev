package com.lzq.service;

import com.lzq.pojo.Stu;

public interface StuService {

    Stu getStuInfo(int id);

    void saveStu();

    void updateStu(int id);

    void deleteStu(int id);

    void saveParent();

    void saveChildren();


}
