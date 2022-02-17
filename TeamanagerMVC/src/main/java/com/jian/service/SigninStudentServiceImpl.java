package com.jian.service;

import com.jian.dao.StudentDao;
import com.jian.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("signinStudentServiceImpl")
public class SigninStudentServiceImpl implements StudentService {



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
    public boolean ifStudentExist(String username) {
    //  如果查询到当前id有人使用
        Student student = studentDao.getStudent(username);

        if(student==null) {
            return false;
        }
        else {
            return true;
        }
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
