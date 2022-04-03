package com.jian.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jian.pojo.OutputTeamMemberData;
import com.jian.pojo.Task;
import com.jian.pojo.Team;
import com.jian.service.StudentTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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

    //todo 通过teamid获取team对象
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

    //通过teamid获取当前team的投票情况
    @RequestMapping(value = "/getVoteState", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, OutputTeamMemberData> getVoteState(@RequestParam(value = "teamId") Integer teamId){
        Map<String,OutputTeamMemberData> voteMap = new HashMap<>();
        List<String> teammateId = studentTeamService.getTeamMateByTeamId(teamId);
        for (String sid : teammateId) {
            OutputTeamMemberData outputTeamMemberData = studentTeamService.getOneVoteState(teamId, sid);
            voteMap.put(sid,outputTeamMemberData);
        }
        return voteMap;
    }

    //通过teamid获取当前登录用户具体投了谁的票
    @RequestMapping(value = "/getVoteStudent", method = RequestMethod.POST)
    @ResponseBody
    public String getVoteStudent(@RequestParam(value = "teamId") Integer teamId,@RequestParam(value = "studentId") String studentId){
        return studentTeamService.getOneVoteStudent(teamId,studentId);
    }

    @RequestMapping(value = "/createTask",method = RequestMethod.POST)
    @ResponseBody
    public String createTask(@RequestParam(value = "teamId") Integer teamId,@RequestParam(value = "context") String taskContext,@RequestParam(value = "taskName") String taskName, @RequestParam(value = "deadline") long deadline, @RequestParam(value = "startTime") long startTime, @RequestParam(value = "studentId") String studentId){
        Integer taskId = studentTeamService.createTask(teamId,taskContext,taskName,deadline,startTime,studentId);
        if (taskId>0){
            return "create";
        }else {
            return "failed";
        }
    }

}
