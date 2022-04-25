package com.jian.controller;


import com.jian.pojo.ContributionMap;
import com.jian.pojo.Student;
import com.jian.pojo.SubTask;
import com.jian.service.StudentTeamService;
import com.jian.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600, methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT}, allowCredentials = "true")
@Controller
public class TaskController {

    @Autowired
    @Qualifier("studentTeamServiceImpl")
    private StudentTeamService studentTeamService;

    @Autowired
    @Qualifier("taskServiceImpl")
    private TaskService taskService;


    @RequestMapping(value = "/createSubTask", method = RequestMethod.POST)
    @ResponseBody
    public Integer createSubTask(@RequestParam(value = "taskId") Integer taskId, @RequestParam(value = "subtaskName") String subTaskName, @RequestParam(value = "subtaskStartTime") long subTaskStartTime, @RequestParam(value = "subtaskDeadline") long subTaskDeadline, @RequestParam(value = "subtaskContent") String subtaskContent) {
        return studentTeamService.createSubTask(taskId, subtaskContent,subTaskName, subTaskDeadline, subTaskStartTime);
    }

    @RequestMapping(value = "/getAllSubtask", method = RequestMethod.POST)
    @ResponseBody
    public List<SubTask> createSubTask(@RequestParam(value = "taskId") Integer taskId) {
        return studentTeamService.getSubTask(taskId);
    }

    @RequestMapping(value = "/finishSubTask", method = RequestMethod.POST)
    @ResponseBody
    public boolean finishSubTask(@RequestParam(value = "subTaskId") Integer subTaskId) {
        return studentTeamService.finishSubTask(subTaskId);
    }

    @RequestMapping(value = "/deleteSubTask", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteSubTask(@RequestParam(value = "subTaskId") Integer subTaskId) {
        return studentTeamService.deleteSubTask(subTaskId);
    }

    @RequestMapping(value = "getContribution", method = RequestMethod.POST)
    @ResponseBody
    public List<ContributionMap> getContribution(@RequestParam(value = "teamId") Integer teamId) {
        return taskService.getStudentContribution(teamId);
    }

    @RequestMapping(value="getAllStoryPoint",method = RequestMethod.POST)
    @ResponseBody
    public Integer getAllStoryPoint(@RequestParam(value = "teamId") Integer teamId){
        return taskService.getAllStoryPoint(teamId);
    }

    @RequestMapping(value="changeScore",method = RequestMethod.POST)
    @ResponseBody
    public Integer changeScore(@RequestParam(value = "teamId") Integer teamId,@RequestParam(value = "studentId") String studentId,@RequestParam(value = "score") Integer score){
        return taskService.changeScore(teamId,score,studentId);
    }

}
