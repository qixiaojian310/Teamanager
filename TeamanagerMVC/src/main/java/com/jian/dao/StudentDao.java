package com.jian.dao;

import com.jian.pojo.Student;

import java.util.List;

public interface StudentDao {
    //得到该学生的task id的list
    public List<Integer> getTaskIdList(String studentId);
    //得到该学生的module id的list
    public List<Integer> getModuleList(String studentId);
    //得到该学生的team id的list
    public List<Integer> getTeamList(String studentId);
    //得到当前student的信息
    public Student getStudent(String studentId);
    //得到当前student的密码
    public String getStudentPwd(String studentId);
    //增加一个student
    public boolean addStudent(Student student);
    //给学生增加team
    public boolean addTeam(String studentId,int teamId);
    //给学生增加module
    public boolean addModule(String studentId,int moduleId);
}
