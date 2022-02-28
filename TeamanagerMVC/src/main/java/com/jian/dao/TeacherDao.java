package com.jian.dao;


import com.jian.pojo.Student;
import com.jian.pojo.Teacher;

public interface TeacherDao {

    //得到当前teacher的信息
    public Teacher getTeacher(String teacherId);
    //得到当前teacher的密码
    public String getTeacherPwd(String teacherId);
    //插入一个新的老师
    public boolean addTeacher(Teacher teacher);


}
