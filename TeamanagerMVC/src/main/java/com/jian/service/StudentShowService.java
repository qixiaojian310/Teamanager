package com.jian.service;

import com.jian.pojo.Student;
import com.jian.pojo.Task;
import com.jian.pojo.Team;

import java.util.List;

public interface StudentShowService {
    //得到该学生的task的list
    public List<Task> getTaskListUnCompleted(String studentId);
    //得到学生的完成的任务的task list
    public List<Task> getTaskListCompleted(String studentId);
    //得到该学生的module id的list
    public List<Integer> getModuleList(String studentId);
    //得到该学生的team id的list(转移到teamService中)
    //public List<Team> getTeamList(String studentId);
    //给学生增加team
    public boolean addTeam(String studentId,int teamId);
    //给学生增加module
    public boolean addModule(String studentId,int moduleId);
    //获得当前登录学生的所有信息
    public Student getStudentNoPwd(String studentId);
    //修改用户的info和username
    public int setStudentNoPwd(String newStudentId,String studentInfo,String oldStudentId);
}
