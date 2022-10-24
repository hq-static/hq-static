package com.aiyun.mybatis.service.impl;

import com.aiyun.mybatis.mapper.StudentMapper;
import com.aiyun.mybatis.model.Student;
import com.aiyun.mybatis.service.IStudentService;

import java.util.List;

public class StudentServiceImpl implements IStudentService {


    private StudentMapper studentMapper; //studentMapper等于接口的实现类

    public StudentMapper getStudentMapper() {
        return studentMapper;
    }

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public int deleteByPrimaryKey(String sid) {
        return studentMapper.deleteByPrimaryKey(sid);
    }

    @Override
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(String sid) {
        return studentMapper.selectByPrimaryKey(sid);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Student> listStudent(Student student) {
        return studentMapper.listStudent(student);
    }
}
