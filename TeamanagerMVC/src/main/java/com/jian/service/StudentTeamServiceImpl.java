package com.jian.service;

import com.jian.dao.*;
import com.jian.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("studentTeamServiceImpl")
public class StudentTeamServiceImpl implements StudentTeamService{

    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    @Autowired
    @Qualifier("teamDao")
    private TeamDao teamDao;

    @Autowired
    @Qualifier("taskDao")
    private TaskDao taskDao;

    @Autowired
    @Qualifier("moduleDao")
    private ModuleDao moduleDao;

    @Autowired
    @Qualifier("chatDao")
    private ChatDao chatDao;

    public ModuleDao getModuleDao() {
        return moduleDao;
    }

    public void setModuleDao(ModuleDao moduleDao) {
        this.moduleDao = moduleDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    //用来获取指定用户的组的对象
    @Override
    public List<Team> getTeamList(String studentId){
        List<Team> teamList = new LinkedList<Team>();
        List<Integer> teamIdList = studentDao.getTeamList(studentId);
        for (Integer teamId : teamIdList) {
            Team team = teamDao.getTeamById(teamId);
            List<String> teammateId = teamDao.getTeammate(teamId);
            List<Student> teammateObjs = new LinkedList<>();
            for (String sid : teammateId) {
                Student student = studentDao.getStduentNoPwd(sid);
                teammateObjs.add(student);
            }
            team.setStudentList(teammateObjs);
            List<Integer> taskIdList = teamDao.getTask(teamId,studentId);
            List<Task> taskList = new LinkedList<Task>();
            for (Integer taskId : taskIdList) {
                Task task = taskDao.getTask(taskId);
                task.setStudentList(taskDao.getStudentList(taskId));
                taskList.add(task);
            }
            team.setTaskList(taskList);
            // TODO 向组中注入组大小
            double moduleSize = moduleDao.getModuleSizeInteger(team.getModuleId());
            double size = moduleSize/(double)teamDao.getTeamNum(teamId);
            team.setTeamSize((int) Math.ceil(size));
            teamList.add(team);
        }
        return teamList;
    }

    //获取某个组的整个组对象
    public Team getTeamByTeamId(Integer teamId){
        Team team = teamDao.getTeamById(teamId);
        List<String> studentIds = teamDao.getTeammate(teamId);
        List<Student> students = new LinkedList<>();
        for (String studentId : studentIds) {
            Student student = studentDao.getStduentNoPwd(studentId);
            students.add(student);
        }
        team.setStudentList(students);
        double moduleSize = moduleDao.getModuleSizeInteger(team.getModuleId());
        double size = moduleSize/(double)teamDao.getTeamNum(teamId);
        team.setTeamSize((int) Math.ceil(size));
        return team;
    }

    public List<String> getTeamMateByTeamId(Integer teamId){
        List<String> studentIds = teamDao.getTeammate(teamId);
        return studentIds;
    }

    @Override
    public OutputTeamMemberData getOneVoteState(Integer teamId, String studentId) {
        Integer voteNumber = studentDao.getVoteNumber(studentId,teamId);
        return new OutputTeamMemberData(voteNumber);
    }

    @Override
    public String getOneVoteStudent(Integer teamId, String studentId) {
        return studentDao.getVoteName(studentId,teamId);
    }

    @Override
    public Team createTeam(int moduleId, String leaderId, String teamName, String chatRoomName) {
        Team teamInject = new Team();
        teamInject.setModuleId(moduleId);
        teamInject.setTeamName(teamName);

        //先添加聊天室
        ChatRoom chatRoomInject = new ChatRoom();
        chatRoomInject.setChatRoomName(chatRoomName);
        chatRoomInject.setTeacher(moduleDao.getTeacherByModuleId(moduleId));
        chatDao.addChatRoom(chatRoomInject);
        //获取chatRoomId
        Integer chatRoomId = chatRoomInject.getChatRoomId();
        teamInject.setChatRoomId(chatRoomId);
        teamDao.addTeam(teamInject);

        Integer teamId = teamInject.getTeamId();
        System.out.println("teamID"+teamId);
        if(teamDao.joinTeam(leaderId,teamId)){
            Team team = getTeamByTeamId(teamId);
            return team;
        }else {
            return null;
        }
    }

    @Override
    public Team joinTeam(int teamId, String studentId) {
        if(teamDao.joinTeam(studentId,teamId)){
            Team team = getTeamByTeamId(teamId);
            return team;
        }else {
            return null;
        }
    }

    //投票
    @Override
    public boolean voteLeader(Integer teamId, String identify, String voteLeader) {
        int state = studentDao.voteLeader(teamId, identify, voteLeader);
        if(state > 1){
            return false;
        }else if(state == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean resetVote(Integer teamId, String identify){
        System.out.println(teamId+"  "+identify);
        int state = studentDao.resetVote(teamId, identify);
        System.out.println("人数为"+state);
        if(state > 1){
            return false;
        }else if (state == 1){
            return true;
        }else {
            return false;
        }

    }


    @Override
    public boolean updateLeader(Integer teamId, String leaderId){
        int state = 0;
        if (Objects.equals(leaderId, "")){
            state = studentDao.updateLeader(teamId, null);
        }else {
            state = studentDao.updateLeader(teamId, leaderId);
        }
        if(state > 1){
            return false;
        }else if (state == 1){
            return true;
        }else {
            return false;
        }

    }

    public Integer createTask(Integer teamId, String taskContext, String taskName, long deadline, long startTime,String studentId){
        Task task = new Task();
        task.setTeamId(teamId);
        task.setTaskName(taskName);
        task.setContext(taskContext);
        Date deadlineDate = new Date(deadline);
        Date startTimeDate = new Date(startTime);
        task.setCompleted(false);
        task.setDeadline(deadlineDate);
        task.setStartTime(startTimeDate);
        taskDao.addTask(task);
        Integer taskId = task.getTaskId();
        taskDao.addTaskStudent(taskId,studentId);
        return taskId;
    }

    public List<Task> getAllTaskInTeams(Integer teamId,String studentId){
        List<Integer> taskIds = teamDao.getAllTaskInTeam(teamId);
        //获取当前用户已经加入的任务
        List<Integer> taskIdForStudent = teamDao.getTask(teamId,studentId);
        taskIds.removeAll(taskIdForStudent);
        List<Task> taskList = new LinkedList<>();
        for (Integer taskId : taskIds) {
            Task task = taskDao.getTask(taskId);
            task.setStudentList(taskDao.getStudentList(taskId));
            taskList.add(task);
        }
        return taskList;
    }

    public boolean joinTask(Integer taskId,String studentId){
        //先查询一下有没有已经加入task
        int taskNum = taskDao.ensureStudentJoinTask(taskId,studentId);
        if(taskNum==0){
            taskDao.addTaskStudent(taskId,studentId);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Task> refreshTask(Integer teamId, String studentId) {
        List<Integer> taskIdForStudent = teamDao.getTask(teamId,studentId);
        List<Task> taskList = new LinkedList<>();
        for (Integer taskId : taskIdForStudent) {
            Task task = taskDao.getTask(taskId);
            task.setStudentList(taskDao.getStudentList(taskId));
            taskList.add(task);
        }
        return taskList;
    }

    @Override
    public Integer createSubTask(Integer taskId, String content, String taskName, long deadline, long startTime) {
        SubTask subTask = new SubTask();
        subTask.setTaskId(taskId);
        subTask.setTaskName(taskName);
        subTask.setContent(content);
        subTask.setDeadline(new Date(deadline));
        subTask.setStartTime(new Date(startTime));
        taskDao.createSubTask(subTask);
        return subTask.getSubTaskId();
    }

    @Override
    public List<SubTask> getSubTask(Integer taskId) {
       List<SubTask> subTasks = taskDao.getAllSubTaskInTask(taskId);
       boolean finishFlag = true;
        for (SubTask subTask : subTasks) {
            if(!subTask.isCompleted()){
                finishFlag = false;
                taskDao.cancelFinishTask(taskId);
            }
        }
        if(finishFlag){
            taskDao.finishTask(taskId);
        }
        return subTasks;

    }

    @Override
    public boolean deleteSubTask(Integer subTaskId) {
        return taskDao.deleteSubTask(subTaskId);
    }

    @Override
    public boolean finishSubTask(Integer subTaskId) {
        return taskDao.finishSubTask(subTaskId);
    }


}
