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
import java.util.Objects;


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
    public List<Module> getAllModule(@RequestBody Teacher teacher) {
        HttpSession session = GetSessionUtil.getSession();
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        try {
            if (!Objects.equals(loginTeacher.getTeacherId(),  teacher.getTeacherId())){
                //session失败
                loginTeacher = teacher;
            }
        } catch (NullPointerException e) {
            loginTeacher = teacher;
        }
        return teacherShowService.getAllModuleTeacher(loginTeacher);
    }

    @RequestMapping(value = "getAllTeacherTeam", method = RequestMethod.POST)
    @ResponseBody
    public List<Team> getAllTeam(@RequestBody Teacher teacher) {
        HttpSession session = GetSessionUtil.getSession();
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        try {
            if (!Objects.equals(loginTeacher.getTeacherId(),  teacher.getTeacherId())){
                //不相等
                loginTeacher = teacher;
            }
        } catch (NullPointerException e) {
            loginTeacher = teacher;
        }
        return teacherShowService.getAllTeamTeacher(loginTeacher);
    }

    @RequestMapping(value = "getCompletedTaskByTeacher", method = RequestMethod.POST)
    @ResponseBody
    public List<Task> getTaskByTeacherCompleted(@RequestBody Teacher teacher) {
        HttpSession session = GetSessionUtil.getSession();
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        try {
            if (!Objects.equals(loginTeacher.getTeacherId(),  teacher.getTeacherId())){
                //session失败
                loginTeacher = teacher;
            }
        } catch (NullPointerException e) {
            loginTeacher = teacher;
        }
        return teacherShowService.getAllTask(loginTeacher, true);
    }

    @RequestMapping(value = "getUnCompletedTaskByTeacher", method = RequestMethod.POST)
    @ResponseBody
    public List<Task> getTaskByTeacherUncompleted(@RequestBody Teacher teacher) {
        HttpSession session = GetSessionUtil.getSession();
        Teacher loginTeacher = (Teacher) session.getAttribute("loginTeacher");
        try {
            if (!Objects.equals(loginTeacher.getTeacherId(), teacher.getTeacherId())){
                //session失败
                loginTeacher = teacher;
            }
        } catch (NullPointerException e) {
            loginTeacher = teacher;
        }
        return teacherShowService.getAllTask(loginTeacher, false);
    }

    @RequestMapping(value = "createModuleByTeacher", method = RequestMethod.POST)
    @ResponseBody
    public Integer createModuleByTeacher(@RequestParam(value = "moduleName", required = false) String moduleName, @RequestParam(value = "teacherId") String teacherId, @RequestParam(value = "moduleSize") Integer moduleSize,@RequestParam(value = "teamNum") Integer teamNum) {
        return teacherShowService.createModuleByTeacher(moduleName,moduleSize,teacherId,teamNum);
    }

    @RequestMapping(value = "getTaskListWithTeamId", method = RequestMethod.POST)
    @ResponseBody
    public List<Task> createModuleByTeacher(@RequestParam(value = "teamId", required = false) Integer teamId) {
        return teacherShowService.getAllTaskInTeam(teamId);
    }

    @RequestMapping(value = "changeModuleName", method = RequestMethod.POST)
    @ResponseBody
    public Integer createModuleByTeacher(@RequestParam(value = "moduleId", required = false) Integer moduleId,@RequestParam(value = "moduleName") String moduleName) {
        return teacherShowService.changeModuleName(moduleId,moduleName);
    }
}
