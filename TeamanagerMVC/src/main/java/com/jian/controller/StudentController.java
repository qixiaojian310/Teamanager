package com.jian.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.jian.pojo.Module;
import com.jian.pojo.Student;
import com.jian.pojo.Task;

import com.jian.pojo.Team;
import com.jian.service.StudentModuleService;
import com.jian.service.StudentShowService;
import com.jian.service.StudentTeamService;
import com.jian.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT},allowCredentials = "true")
@Controller
public class StudentController {
    @Autowired
    @Qualifier("studentShowServiceImpl")
    private StudentShowService studentshowService;

    @Autowired
    @Qualifier("studentTeamServiceImpl")
    private StudentTeamService studentTeamService;

    @Autowired
    @Qualifier("studentModuleServiceImpl")
    private StudentModuleService studentModuleService;

//学生一进来通过自己的学生id查询到所有的课程
//    @RequestMapping(value = "/getmodule", method = RequestMethod.POST)
//    @ResponseBody
//    public List<Module> getmodule(@RequestBody String studentId) throws JsonProcessingException {
//        return moduleService.getModuleStudent(studentId);
//    }

    //学生一进来通过自己的学生id查询到所有的team
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

    @RequestMapping(value = "/getUnCompletedTaskByStudentId", method = RequestMethod.POST)
    @ResponseBody
    //学生一登陆就可以获得自己所有的未完成task
    public List<Task> getUnCompletedTaskByStudentId(@RequestBody Student student){
        List<Task> taskList = studentshowService.getTaskListUnCompleted(student.getStudentId());
        for (Task task : taskList) {
            System.out.println(task);
        }
        return taskList;
    }

    @RequestMapping(value = "/getCompletedTaskByStudentId", method = RequestMethod.POST)
    @ResponseBody
    //学生一登陆就可以获得自己所有的完成task
    public List<Task> getCompletedTaskByStudentId(@RequestBody Student student){
        List<Task> taskList = studentshowService.getTaskListCompleted(student.getStudentId());
        for (Task task : taskList) {
            System.out.println(task);
        }
        return taskList;
    }

    //学生通过学生ID获得所有的所在的组
    @RequestMapping(value = "/getAllTeam",method = RequestMethod.POST)
    @ResponseBody
    public List<Team> getAllTeam(@RequestBody Student student){
        List<Team> teamList = studentTeamService.getTeamList(student.getStudentId());
        return teamList;
    }

    //TODO 学生通过学生的ID或者所有有他的课程
    @RequestMapping(value = "/getAllModule",method = RequestMethod.POST)
    @ResponseBody
    public List<Module> getAllModule(@RequestBody Student student){
        List<Module> moduleList = studentModuleService.getAllModule(student.getStudentId());
        return moduleList;
    }
    @RequestMapping(value = "/getStudentInfo",method = RequestMethod.POST)
    @ResponseBody
    public Student getStudentNoPwd(@RequestBody Student student){
        student = studentshowService.getStudentNoPwd(student.getStudentId());
        return student;
    }

    @RequestMapping(value = "/setStudentNoPwd",method = RequestMethod.POST)
    @ResponseBody
    public int setStudentNoPwd(@RequestBody Student student){
        String studentId = student.getStudentId();
        String studentInfo = student.getStudentInfo();
        HttpSession session = GetSessionUtil.getSession();
        Student loginStudent = (Student) session.getAttribute("loginStudent");
        System.out.println(session);
        System.out.println("登录的student id 是"+loginStudent.getStudentId());
        String loginStudentId = loginStudent.getStudentId();
        return studentshowService.setStudentNoPwd(studentId,studentInfo,loginStudentId);
    }

    @RequestMapping(value = "getAllModuleSearch",method = RequestMethod.POST)
    @ResponseBody
    public List<Module> getAllModuleSearch(){
        List<Module> moduleList = studentModuleService.getAllModuleSearch();
        return moduleList;
    }

    @RequestMapping(value = "joinModule",method = RequestMethod.POST)
    @ResponseBody
    public boolean joinModule(@RequestParam(value = "studentId",required = true) String studentId,@RequestParam(value = "moduleId",required = true) Integer moduleId){
        return studentModuleService.joinModule(studentId,moduleId);
    }


    @RequestMapping(value = "deleteSelectStudent",method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteSelectStudent(@RequestParam("moduleId") int moduleId, @RequestParam("studentIds") String[] studentIds){
        return studentModuleService.deleteStudentInModule(studentIds,moduleId);
    }


    @RequestMapping(value = "createTeam",method = RequestMethod.POST)
    @ResponseBody
    public Team createTeam(@RequestParam("moduleId") int moduleId,@RequestParam("leaderId") String leaderId, @RequestParam("teamName") String teamName,@RequestParam("chatRoomName") String chatRoomName){
        Team res = studentTeamService.createTeam(moduleId,leaderId,teamName,chatRoomName);
        if(res == null){
            Team team = new Team();
            team.setTeamId(-1);
            return team;
        }else {
            return res;
        }
    }

    @RequestMapping(value = "joinTeam",method = RequestMethod.POST)
    @ResponseBody
    public Team joinTeam(@RequestParam("studentId") String studentId, @RequestParam("teamId") int teamId){
        Team res = studentTeamService.joinTeam(teamId,studentId);
        if(res == null){
            Team team = new Team();
            team.setTeamId(-1);
            return team;
        }else {
            return res;
        }
    }

    @RequestMapping(value = "/voteLeader",method = RequestMethod.POST)
    @ResponseBody
    public boolean voteLeader(@RequestParam("teamId") Integer teamId, @RequestParam("identify") String identify, @RequestParam("voteStu") String voteStudent){
        try {
            return studentTeamService.voteLeader(teamId, identify, voteStudent);
        } catch (Exception e){
            System.out.println("duplicate vote leader");
            return false;
        }
    }

    @RequestMapping(value = "/resetVote", method = RequestMethod.POST)
    @ResponseBody
    public boolean resetVote(@RequestParam("teamId") Integer teamId, @RequestParam("identify") String identify){
        try{
            return studentTeamService.resetVote(teamId, identify);
        }catch (Exception e){
            System.out.println("duplicate vote leader");
            return false;
        }
    }

    //更新leader
    @RequestMapping(value = "/updateLeader", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateLeader(@RequestParam("teamId") Integer teamId, @RequestParam("leader") String leader){
        try{
            return studentTeamService.updateLeader(teamId, leader);
        }catch (Exception e){
            System.out.println("duplicate vote leader");
            return false;
        }
    }

//    @RequestMapping(value = "getStudent",method = RequestMethod.POST)
//    @ResponseBody
//    public Student getStudent(@RequestParam("studentId") String studentId){
//
//    }

    //学生通过moduleid得到在该课程下的所有task
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
//            task.setStudents(taskService.getStudents(taskid));
//            taskList.add(task);
//        }
//        return taskList;
//    }

    //学生通过teamid得到在该课程下的所有task
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
//            task.setStudents(taskService.getStudents(taskid));
//            taskList.add(task);
//        }
//        return taskList;
//    }

    //学生通过teamid知道自己是不是组长
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

    //组长增加组员
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

    //学生得到当前task的完成度
//    @RequestMapping(value = "/getprogress", method = RequestMethod.POST)
//    @ResponseBody
//    public int getprogress(@RequestBody int taskId,String studentId) throws JsonProcessingException {
//        return taskServive.getTaskProgress(taskId,studentId);
//    }

}
