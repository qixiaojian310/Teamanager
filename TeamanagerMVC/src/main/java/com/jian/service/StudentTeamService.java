package com.jian.service;

import com.jian.pojo.OutputTeamMemberData;
import com.jian.pojo.Team;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentTeamService {

    //根据学生名字返回一整个组的数组
    public List<Team> getTeamList(String studentId);

    //根据组号列表返回组的对象
    public Team getTeamByTeamId(Integer teamId);

    //学生加入一个空组
    public Team createTeam(int moduleId, String leaderId, String teamName, String chatRoomName);

    //学生加入一个存在的组
    public Team joinTeam(int teamId, String studentId);

    //获取所有组员的id
    public List<String> getTeamMateByTeamId(Integer teamId);

    //获取某个组员的投票情况
    public OutputTeamMemberData getOneVoteState(Integer teamId, String studentId);

    //获取某个用户的投票情况
    public String getOneVoteStudent(Integer teamId, String studentId);

    //投票
    public boolean voteLeader(Integer teamId, String identify, String voteLeader);
    //撤销投票
    public boolean resetVote(Integer teamId, String identify);

    public boolean updateLeader(Integer teamId, String leader);

    public Integer createTask(Integer teamId, String taskContext, String taskName, long deadline, long startTime, String studentId);
}
