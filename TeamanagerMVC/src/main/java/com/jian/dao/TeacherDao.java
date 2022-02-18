package com.jian.dao;


import com.jian.pojo.Teacher;

public interface TeacherDao {

    //得到当前teacher的信息
    public Teacher getTeacher(String teacherId);
    //得到当前teacher的密码
    public String getTeacherPwd(String teacherId);

}
