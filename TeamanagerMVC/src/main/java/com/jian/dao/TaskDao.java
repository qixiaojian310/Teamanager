package com.jian.dao;


import com.jian.pojo.Student;
import com.jian.pojo.Task;

import java.sql.Date;
import java.util.List;

public interface TaskDao {
    //这个地方只能获取除了组员之外的task信息
    public Task getTask(String taskId);
    //获取该task的组员信息
    public  List<Student>  getStudentList(String taskId);
    //增加一个空的任务
    public boolean addTask(Task task);
    //给该任务添加学生
    public boolean addTaskStudent(String taskId,String studentId);


}
