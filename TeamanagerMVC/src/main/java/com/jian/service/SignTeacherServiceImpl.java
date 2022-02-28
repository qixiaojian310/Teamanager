package com.jian.service;

import com.jian.dao.TeacherDao;
import com.jian.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("signTeacherServiceImpl")
public class SignTeacherServiceImpl implements SignTeacherService{

    @Autowired
    @Qualifier("teacherDao")
    private TeacherDao teacherDao;

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public Teacher ifTeacherExist(String username) {
        Teacher teacher = teacherDao.getTeacher(username);
        return teacher;
    }

    @Override
    public String getTeacherPwd(String username) {
        return teacherDao.getTeacherPwd(username);
    }

    @Override
    public boolean insertNewTeacher(Teacher user) {
        return teacherDao.addTeacher(user);
    }
}
