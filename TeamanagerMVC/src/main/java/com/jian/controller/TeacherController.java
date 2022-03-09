package com.jian.controller;


import com.jian.pojo.*;
import com.jian.pojo.Module;
import com.jian.service.TeacherShowService;
import com.jian.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600, methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT}, allowCredentials = "true")
@Controller
public class TeacherController {

    @Autowired
    @Qualifier("teacherShowServiceImpl")
    private TeacherShowService teacherShowService;

    public TeacherShowService getTeacherShowService() {
        return teacherShowService;
    }

    public void setTeacherShowService(TeacherShowService teacherShowService) {
        this.teacherShowService = teacherShowService;
    }

    @RequestMapping(value = "/getAllTeacherModule", method = RequestMethod.POST)
    @ResponseBody
    public List<Module> getAllModule() {
        HttpSession session = GetSessionUtil.getSession();
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        return teacherShowService.getAllModuleTeacher(loginTeacher);
    }

    @RequestMapping(value = "getAllTeacherTeam", method = RequestMethod.POST)
    @ResponseBody
    public List<Team> getAllTeam() {
        HttpSession session = GetSessionUtil.getSession();
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        return teacherShowService.getAllTeamTeacher(loginTeacher);
    }

    @RequestMapping(value = "getCompletedTaskByTeacher", method = RequestMethod.POST)
    @ResponseBody
    public List<Task> getTaskByTeacherCompleted() {
        HttpSession session = GetSessionUtil.getSession();
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        return teacherShowService.getAllTask(loginTeacher, true);
    }

    @RequestMapping(value = "getUnCompletedTaskByTeacher", method = RequestMethod.POST)
    @ResponseBody
    public List<Task> getTaskByTeacherUncompleted() {
        HttpSession session = GetSessionUtil.getSession();
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        return teacherShowService.getAllTask(loginTeacher, false);
    }

    @RequestMapping(value = "createModuleByTeacher", method = RequestMethod.POST)
    @ResponseBody
    public Integer createModuleByTeacher(@RequestParam(value = "moduleName", required = false) String moduleName, @RequestParam(value = "teacherId") String teacherId, @RequestParam(value = "moduleSize") Integer moduleSize,@RequestParam(value = "teamNum") Integer teamNum) {
        return teacherShowService.createModuleByTeacher(moduleName,moduleSize,teacherId,teamNum);
    }
}
