package com.aiyun.mybatis.service;

import com.aiyun.mybatis.model.Student;

import java.util.List;

public interface IStudentService {
    int deleteByPrimaryKey(String sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    //查询全部学生
    List<Student> listStudent(Student student);
}