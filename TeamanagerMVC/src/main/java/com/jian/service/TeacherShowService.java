package com.jian.service;

import com.jian.pojo.Module;
import com.jian.pojo.Task;
import com.jian.pojo.Teacher;
import com.jian.pojo.Team;

import java.util.List;

public interface TeacherShowService {
    public List<Module> getAllModuleTeacher(Teacher teacher);
    public List<Team> getAllTeamTeacher(Teacher teacher);
    public List<Task> getAllTask(Teacher teacher,boolean completed);
    public Integer createModuleByTeacher(String moduleName,Integer moduleSize,String teacherId);
}
