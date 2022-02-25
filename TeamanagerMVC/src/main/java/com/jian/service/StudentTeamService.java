package com.jian.service;

import com.jian.pojo.Team;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentTeamService {

    //根据组名返回一整个组的数组
    public List<Team> getTeamList(String studentId);
}
