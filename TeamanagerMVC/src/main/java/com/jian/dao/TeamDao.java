package com.jian.dao;


import com.jian.pojo.Student;
import com.jian.pojo.Task;
import com.jian.pojo.Team;

import java.util.List;

public interface TeamDao {
    //跟据teamname和sutdentid找到team的id
  public Integer getTeamId(String teamName,String studentId);
  //跟据team的id和学生的id找到该学生在这个队伍里的task id
  public Integer getTask(int teamId,String studentId);
  //该team的leader
  public  String isLeader(int teamId);
  //得到该学生的所有队伍id
  public List<Integer> getTeam(String studentId);
  //老师有权限给某课程添加team
  public boolean addTeam(String leaderId,int moduleId,String teamName);
  //老师有权查询该team下的所有task
  public List<Integer> getTaskList(int teamId);

}