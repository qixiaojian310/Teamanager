package com.jian.dao;

import com.jian.pojo.Student;

import java.util.List;

public interface StudentDao {
    //得到该学生的未完成task id的list
    public List<Integer> getUnCompletedTaskIdList(String studentId,boolean completed);
    //得到该学生的module id的list
    public List<Integer> getModuleList(String studentId);
    //得到该学生的team id的list
    public List<Integer> getTeamList(String studentId);
    //得到当前student的信息
    public Student getStudent(String studentId);
    //得到一个没有密码的student对象
    public Student getStduentNoPwd(String studentId);
    //得到当前student的密码
    public String getStudentPwd(String studentId);
    //增加一个student
    public boolean addStudent(Student student);
    //给学生增加team
    public boolean addTeam(String studentId,int teamId);
    //给学生增加module
    public boolean addModule(String studentId,int moduleId);
    //修改学生的信息
    public int setStudentNoPwd(String newStudentId,String studentInfo,String oldStudentId);
    //删除学生的指定课程
    public boolean deleteModuleByStudentId(String studentId,int moduleId);
    public boolean deleteStudentInTeam(String studentId,int moduleId);
    public boolean deleteStudentInTask(String studentId,int moduleId);
    //获取一个学生的投票结果
    public String getVoteName(String studentId,int teamId);
    //获取投票某个学生的总人数
    public Integer getVoteNumber(String studentId, int teamId);
}
