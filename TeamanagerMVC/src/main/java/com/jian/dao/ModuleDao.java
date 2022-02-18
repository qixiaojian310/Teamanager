package com.jian.dao;


import java.util.List;

public interface ModuleDao {
    //通过学生id和课程名确定一下课程的id
    public Integer getModuleId(String moduleName,String studentId);
    //增加一个课程
    public boolean addModule(String teacherId,String moduleName);
    //通过老师id和课程名确定一下课程的id
    public String getModuleIdbyTeacher(String teacherId,String moduleName);
    //给课程里加入学生(需知道课程id）
    public boolean addModuleStudent(String moduleId,String studentId);
    //通过课程id和学生id确定一下学生在当前课程有什么任务
    public List<Integer> getTask(int moduleId,String studentId);

}
