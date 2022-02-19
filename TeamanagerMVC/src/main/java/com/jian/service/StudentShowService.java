package com.jian.service;

import com.jian.pojo.Student;

import java.util.List;

public interface StudentShowService {
    //得到该学生的task id的list
    public List<Integer> getTaskIdList(String studentId);
    //得到该学生的module id的list
    public List<Integer> getModuleList(String studentId);
    //得到该学生的team id的list
    public List<Integer> getTeamList(String studentId);
    //给学生增加team
    public boolean addTeam(String studentId,int teamId);
    //给学生增加module
    public boolean addModule(String studentId,int moduleId);
}
