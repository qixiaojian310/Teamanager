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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.LinkedList;
import java.util.List;


@Service("studentShowServiceImpl")
public class StudentShowServiceImpl implements StudentShowService {

    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    @Autowired
    @Qualifier("taskDao")
    private TaskDao taskDao;

    @Autowired
    @Qualifier("teamDao")
    private TeamDao teamDao;

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    @Override
    public List<Task> getTaskListUnCompleted(String studentId){
        List<Integer> taskListIdTemp = studentDao.getUnCompletedTaskIdList(studentId,false);
        List<Task> taskListTemp = new LinkedList<Task>();

        for (Integer taskId : taskListIdTemp) {
            System.out.println("taskId"+taskId);
            Task task = taskDao.getTask(taskId);
            Integer progress = taskDao.getTaskProgress(taskId,studentId);
            task.setProgress(progress);
            List<Student> studentList = taskDao.getStudentList(taskId);
            task.setStudentList(studentList);
            taskListTemp.add(task);
        }
        return taskListTemp;
    }
    @Override
    public List<Task> getTaskListCompleted(String studentId){
        List<Integer> taskListIdTemp = studentDao.getUnCompletedTaskIdList(studentId,true);
        List<Task> taskListTemp = new LinkedList<Task>();

        for (Integer taskId : taskListIdTemp) {
            System.out.println("taskId"+taskId);
            Task task = taskDao.getTask(taskId);
            task.setProgress(100);
            List<Student> studentList = taskDao.getStudentList(taskId);
            task.setStudentList(studentList);
            taskListTemp.add(task);
        }
        return taskListTemp;
    }

    @Override
    public Student getStudentNoPwd(String studentId){
        Student student = studentDao.getStduentNoPwd(studentId);
        return student;
    }

    @Override
    public int setStudentNoPwd(String newStudentId,String studentInfo,String oldStudentId){
        return studentDao.setStudentNoPwd(newStudentId, studentInfo, oldStudentId);
    }



    @Override
    public List<Integer> getModuleList(String studentId){
        return studentDao.getModuleList(studentId);
    }

    @Override
    public boolean addTeam(String studentId,int teamId){
        return studentDao.addTeam(studentId,teamId);
    }
    @Override
    public boolean addModule(String studentId,int moduleId){
        return studentDao.addModule(studentId,moduleId);
    }
}
