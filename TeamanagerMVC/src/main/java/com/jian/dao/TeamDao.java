package com.jian.dao;

import com.jian.pojo.Team;

import java.util.List;

public interface TeamDao {

  //跟据team的id和学生的id找到该学生在这个队伍里的task id
  public List<Integer> getTask(int teamId,String studentId);
  //该team的leader
  public  String isLeader(int teamId);
  //得到该学生的所有队伍id
  public List<Integer> getTeam(String studentId);
  //插入一个新的team,返回team的id
  public Integer addTeam(Team team);
  //学生加入一个team
  public boolean joinTeam(String studentId, int teamId);
  //老师有权查询该team下的所有task
  public List<Integer> getTaskList(int teamId,boolean completed);
  //查询某个学生所有完成的Task
  public List<Integer> getAllTaskByStudent(String studentId,int teamId,boolean completed);
  //通过teamid得到整个team的信息
  public Team getTeamById(int teamId);
  //跟据teamid得到当前team有多个队友
  public List<String> getTeammate(int teamId);
  //得到当前team的额定大小
  public Integer getTeamNum(int teamId);
  //获取某个组所有的taskId
  public List<Integer> getAllTaskInTeam(int teamId);

}
