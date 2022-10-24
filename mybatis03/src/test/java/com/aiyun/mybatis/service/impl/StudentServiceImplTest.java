package com.aiyun.mybatis.service.impl;

import com.aiyun.mybatis.mapper.StudentMapper;
import com.aiyun.mybatis.model.Student;
import com.aiyun.mybatis.service.IStudentService;
import com.aiyun.mybatis.utils.MybatisSqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class StudentServiceImplTest {

    private IStudentService studentService;
    private Student student;

    @Before
    public void setUp() throws Exception {
        //初始化
        student=new Student();

        //获取SqlSession
        SqlSession sqlSession=MybatisSqlSessionFactoryUtil.getSqlSession();

        //从mybatis中获取mapper接口的实现
        StudentMapper mapper=sqlSession.getMapper(StudentMapper.class);

        //将mapper接口的实现设置到StudentServiceImpl中
        StudentServiceImpl studentServiceImpl=new StudentServiceImpl();
        studentServiceImpl.setStudentMapper(mapper);


        studentService=studentServiceImpl;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        student.setSname("lili");
        student.setSsex("男");
        student.setSid("9999");
        student.setSage(new Date());

        studentService.insert(student);
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void listStudent() {
    }
}