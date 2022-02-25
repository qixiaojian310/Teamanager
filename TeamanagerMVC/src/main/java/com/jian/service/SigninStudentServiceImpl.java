package com.jian.service;

import com.jian.dao.StudentDao;
import com.jian.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("signinStudentServiceImpl")
public class SigninStudentServiceImpl implements SignStudentService {



    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    @Override
    public Student ifStudentExist(String username) {
    //  如果查询到当前id有人使用
        Student student = studentDao.getStudent(username);
        return student;
    }

    @Override
    public String getStudentPwd(String username) {

        return studentDao.getStudentPwd(username);
    }

    @Override
    public boolean insertNewStudent(Student user){
        return studentDao.addStudent(user);
    }
}
