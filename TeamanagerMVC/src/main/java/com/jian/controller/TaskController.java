package com.jian.controller;


import com.jian.pojo.SubTask;
import com.jian.service.StudentTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600, methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT}, allowCredentials = "true")
@Controller
public class TaskController {

    @Autowired
    @Qualifier("studentTeamServiceImpl")
    private StudentTeamService studentTeamService;


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


}
