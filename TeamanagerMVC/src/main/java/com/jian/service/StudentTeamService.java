package com.jian.service;

import com.jian.pojo.Team;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentTeamService {

    //根据学生名字返回一整个组的数组
    public List<Team> getTeamList(String studentId);

    //根据组号列表返回组的对象
    public Team getTeamByTeamId(Integer teamId);

    //学生加入一个空组
    public Team createTeam(int moduleId,String leaderId,String teamName);
    //学生加入一个存在的组
    public Team joinTeam(int teamId,String studentId);
}
