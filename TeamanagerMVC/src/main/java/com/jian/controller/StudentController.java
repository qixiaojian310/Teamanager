//package com.jian.controller;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.jian.pojo.Module;
//import com.jian.pojo.Task;
//
//import com.jian.pojo.Team;
//import com.jian.service.StudentShowService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT})
//@Controller
//public class StudentController {
//    @Autowired
//    @Qualifier("StudentShowServiceImpl")
//    private StudentShowService studentshowService;
//
//
////学生一进来通过自己的学生id查询到所有的课程
//    @RequestMapping(value = "/getmodule", method = RequestMethod.POST)
//    @ResponseBody
//    public List<Module> getmodule(@RequestBody String studentId) throws JsonProcessingException {
//        return moduleService.getModuleStudent(studentId);
//    }
//
//    //学生一进来通过自己的学生id查询到所有的team
//    @RequestMapping(value = "/getteam", method = RequestMethod.POST)
//    @ResponseBody
//    public List<Team> getteam(@RequestBody String studentId) throws JsonProcessingException {
//        List<Integer> teamidList;
//        List<Team> teamList = new ArrayList<Team>();
//        teamidList =  studentshowService.getTeamList(studentId);
//        for(int i=0;i< teamidList.size();i++){
//            int teamid = teamidList.get(i);
//            Team team;
//            team = teamService.getTeamById(teamid);
//            teamList.add(team);
//
//        }
//        return teamList;
//    }
//
//    //学生通过moduleid得到在该课程下的所有task
//    @RequestMapping(value = "/gettaskbymodule", method = RequestMethod.POST)
//    @ResponseBody
//    public List<Task> gettaskbymodule(@RequestBody int moduleId,String studentId) throws JsonProcessingException {
//        List<Integer> taskidList ;
//        List<Task> taskList = new ArrayList<Task>();
//        taskidList = moduleService.getTask(moduleId,studentId);
//        for(int i=0;i< taskidList.size();i++){
//            int taskid = taskidList.get(i);
//            Task task ;
//            task = taskService.getTask(taskid);
//            task.setStudentList(taskService.getStudentList(taskid));
//            taskList.add(task);
//        }
//        return taskList;
//    }
//
//    //学生通过teamid得到在该课程下的所有task
//    @RequestMapping(value = "/gettaskbyteam", method = RequestMethod.POST)
//    @ResponseBody
//    public List<Task> gettaskbyteam(@RequestBody int teamId,String studentId) throws JsonProcessingException {
//        List<Integer> taskidList ;
//        List<Task> taskList = new ArrayList<Task>();
//        taskidList = teamService.getTask(teamId,studentId);
//        for(int i=0;i< taskidList.size();i++){
//            int taskid = taskidList.get(i);
//            Task task ;
//            task = taskService.getTask(taskid);
//            task.setStudentList(taskService.getStudentList(taskid));
//            taskList.add(task);
//        }
//        return taskList;
//    }
//
//    //学生通过teamid知道自己是不是组长
//    @RequestMapping(value = "/isleader", method = RequestMethod.POST)
//    @ResponseBody
//    public boolean isleader(@RequestBody int teamId,String studentId) throws JsonProcessingException {
//        String leaderid = teamService.isLeader(teamId);
//
//        if(studentId.equals(leaderid)){
//            return true;
//        }
//        return false;
//    }
//
//    //组长增加组员
//    @RequestMapping(value = "/addteammate", method = RequestMethod.POST)
//    @ResponseBody
//    public boolean addteammate(@RequestBody int teamId,String studentId) throws JsonProcessingException {
//    return studentshowService.addTeam(studentId,teamId);
//    }
//
//    //组长分配任务 并给每个任务分配组员
//    @RequestMapping(value = "/addtask", method = RequestMethod.POST)
//    @ResponseBody
//    public boolean addtask(@RequestBody Task task,String studentId) throws JsonProcessingException {
//        Integer taskId = taskService.addTask(task);
//        return taskService.addTaskStudent(taskId,studentId);
//    }
//
//    //学生得到当前task的完成度
//    @RequestMapping(value = "/getprogress", method = RequestMethod.POST)
//    @ResponseBody
//    public int getprogress(@RequestBody int taskId,String studentId) throws JsonProcessingException {
//        return taskServive.getTaskProgress(taskId,studentId);
//    }
//
//}
