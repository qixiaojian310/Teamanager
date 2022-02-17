package com.jian.dao;


import com.jian.pojo.Student;
import com.jian.pojo.Task;

import java.sql.Date;
import java.util.List;

public interface TaskDao {
    public List<Task> getTaskList();
    public  List<Student>  getStudentList(String taskId);
    public String getContext(String taskId);
    public String getTeamId(String taskId);
    public Date getDeadline(String taskId);


}
