package com.jian.service;

import com.jian.dao.ModuleDao;
import com.jian.dao.StudentDao;
import com.jian.dao.TaskDao;
import com.jian.dao.TeamDao;
import com.jian.pojo.*;
import com.jian.pojo.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service("teacherShowServiceImpl")
public class TeacherShowServiceImpl implements TeacherShowService{

    @Autowired
    @Qualifier("moduleDao")
    private ModuleDao moduleDao;

    public ModuleDao getModuleDao() {
        return moduleDao;
    }

    public void setModuleDao(ModuleDao moduleDao) {
        this.moduleDao = moduleDao;
    }

    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Autowired
    @Qualifier("teamDao")
    private TeamDao teamDao;

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Autowired
    @Qualifier("taskDao")
    private TaskDao taskDao;

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    //TODO 返回老师的所有Module对象
    @Override
    public List<Module> getAllModuleTeacher(Teacher teacher) {
        List<Module> moduleList = moduleDao.getModuleTeacher(teacher.getTeacherId());
        for (Module module : moduleList) {
            module.setTeacher(teacher);
            List<String> studentIdList = moduleDao.getStudentsByModuleId(module.getModuleId());
            List<Student> students = new LinkedList<Student>();
            for (String sid : studentIdList) {
                Student student = studentDao.getStduentNoPwd(sid);
                students.add(student);
            }
            module.setStudents(students);
            module.setTeamIds(moduleDao.getTeamByModule(module.getModuleId()));
        }
        return moduleList;
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

    @Override
    public List<Task> getAllTaskInTeam(Integer teamId) {
        List<Task> taskList = new LinkedList<>();
        List<Integer> taskIdList = teamDao.getAllTaskInTeam(teamId);
        for (Integer integer : taskIdList) {
            Task task = taskDao.getTask(integer);
            task.setStudentList(taskDao.getStudentList(integer));
            task.setStoryPoint(taskDao.getStoryPoint(integer));
            taskList.add(task);
        }
        return taskList;
    }

    @Override
    public Integer changeModuleName(Integer moduleId, String moduleName) {
        return moduleDao.changeModuleName(moduleId, moduleName);
    }

    @Override
    public List<Team> getAllTeamTeacher(Teacher teacher) {
        List<Module> moduleList = moduleDao.getModuleTeacher(teacher.getTeacherId());
        List<Team> teamList = new LinkedList<Team>();
        for (Module module : moduleList) {
            List<Integer> teamIdList = moduleDao.getTeamByModule(module.getModuleId());
            for (Integer teamId : teamIdList) {
                Team team = teamDao.getTeamById(teamId);
                List<String> teammateId = teamDao.getTeammate(teamId);
                List<Student> teammateObjs = new LinkedList<>();
                for (String sid : teammateId) {
                    Student student = studentDao.getStduentNoPwd(sid);
                    teammateObjs.add(student);
                }
                team.setStudentList(teammateObjs);
                List<Integer> taskIdList = teamDao.getAllTaskInTeam(teamId);
                List<Task> taskList = new LinkedList<Task>();
                for (Integer taskId : taskIdList) {
                    Task task = taskDao.getTask(taskId);
                    task.setStudentList(taskDao.getStudentList(taskId));
                    task.setStoryPoint(taskDao.getStoryPoint(taskId));
                    taskList.add(task);
                }
                team.setTaskList(taskList);
                // TODO 向组中注入组大小
                double moduleSize = moduleDao.getModuleSizeInteger(team.getModuleId());
                double size = moduleSize/(double)teamDao.getTeamNum(teamId);
                team.setTeamSize((int) Math.ceil(size));
                teamList.add(team);
            }
        }
        return teamList;
    }

    @Override
    public List<Task> getAllTask(Teacher teacher,boolean completed) {
        List<Task> taskList = new LinkedList<Task>();
        List<Team> teamList =  getAllTeamTeacher(teacher);
        for (Team team : teamList) {
            List<Integer> taskListTemp = teamDao.getTaskList(team.getTeamId(),completed);
            for (Integer taskId : taskListTemp) {
                Task task = taskDao.getTask(taskId);
                Integer progress = taskDao.getTaskProgressAvg(taskId);
                task.setProgress(progress);
                List<Student> studentList = taskDao.getStudentList(taskId);
                task.setStudentList(studentList);
                taskList.add(task);
            }
        }
        return taskList;
    }

    @Override
    public Integer createModuleByTeacher(String moduleName,Integer moduleSize,String teacherId,Integer teamNum) {
        return moduleDao.addModule(teacherId,moduleName,moduleSize,teamNum);
    }


}
