package com.jian.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jian.pojo.Task;
import com.jian.pojo.Team;
import com.jian.service.StudentTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT},allowCredentials = "true")
@Controller
public class TeamController {


    @Autowired
    @Qualifier("studentTeamServiceImpl")
    private StudentTeamService studentTeamService;

    public StudentTeamService getStudentTeamService() {
        return studentTeamService;
    }

    public void setStudentTeamService(StudentTeamService studentTeamService) {
        this.studentTeamService = studentTeamService;
    }

    @RequestMapping(value = "/getAllTeamByTeamIds",method = RequestMethod.POST)
    @ResponseBody
    public List<Team> getTeamsByTeamIds(@RequestParam(value = "teamIds",required = false) int[] teamIds) throws NullPointerException{
        List<Team> TeamList = new LinkedList<Team>();
        //这说明teamIds没有传值这时候直接返回一个空的对象
        if(teamIds == null){
            return TeamList;
        }
        for (int teamId : teamIds) {
            TeamList.add(studentTeamService.getTeamByTeamId(teamId));
        }
        return TeamList;
    }


}
