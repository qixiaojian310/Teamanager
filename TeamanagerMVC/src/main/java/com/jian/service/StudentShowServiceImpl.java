package com.jian.service;

import com.jian.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentShowServiceImpl")
public class StudentShowServiceImpl implements StudentShowService {

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
    public List<Integer> getTaskIdList(String studentId){
        return studentDao.getTaskIdList(studentId);
    }

    @Override
    public List<Integer> getModuleList(String studentId){
        return studentDao.getModuleList(studentId);
    }

    @Override
    public List<Integer> getTeamList(String studentId){
        return studentDao.getTeamList(studentId);
    }

    @Override
    public boolean addTeam(String studentId,int teamId){
        return studentDao.addTeam(studentId,teamId);
    }
    @Override
    public boolean addModule(String studentId,int moduleId){
        return studentDao.addModule(studentId,moduleId);
    }
}
