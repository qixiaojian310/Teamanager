package com.jian.service;

import com.jian.dao.StudentDao;
import com.jian.dao.TaskDao;
import com.jian.dao.TeamDao;
import com.jian.pojo.Student;
import com.jian.pojo.Task;
import com.jian.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


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
            List<Student> teammateObjs = new LinkedList<Student>();
            for (String sid : teammateId) {
                Student student = new Student();
                student.setStudentId(sid);
                System.out.println("sid"+sid);
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
            teamList.add(team);
        }
        return teamList;
    }

    public Team getTeamByTeamId(Integer teamId){
        Team team = teamDao.getTeamById(teamId);
        List<String> studentIds = teamDao.getTeammate(teamId);
        List<Student> students = new LinkedList<Student>();
        for (String studentId : studentIds) {
            students.add(studentDao.getStduentNoPwd(studentId));
        }
        team.setStudentList(students);
        return team;
    }
}
