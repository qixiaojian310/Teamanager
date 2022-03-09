package com.jian.dao;


import com.jian.pojo.Module;
import com.jian.pojo.Teacher;

import java.util.List;

public interface ModuleDao {
    //通过学生id和课程名确定一下课程的id
    public Integer getModuleId(String moduleName,String studentId);
    //增加一个课程 返回他的moduleid
    public int addModule(String teacherId, String moduleName, Integer moduleSize, Integer teamNum);
    //通过老师id和课程名确定一下课程的id
    public String getModuleIdbyTeacher(String teacherId,String moduleName);
    //给课程里加入学生(需知道课程id）
    public boolean addModuleStudent(String moduleId,String studentId);
    //通过课程id和学生id确定一下学生在当前课程有什么任务
    public List<Integer> getTask(int moduleId,String studentId);
    //学生需要知道他上了什么课
    public List<Module> getModuleStudent(String studentId);
    //老师需要知道他上了什么课
    public List<Module> getModuleTeacher(String teacherId);
    //跟据moduleid得到对应的所有的team的id
    public List<Integer> getTeamByModule(Integer moduleId);
    //跟据课程id得到该课程包含的所有的学生
    public List<String> getStudent(String moduleId);
    //获得对应的老师(通过课程名）
    public Teacher getTeacherByModuleId(Integer moduleId);
    //通过课程ID获得所有的学生Id
    public List<String> getStudentsByModuleId(Integer moduleId);

    public List<Module> getAllModule();
    //通过课程Id获取课程的额定人数‘
    public Integer getModuleSizeInteger(Integer moduleId);
}
