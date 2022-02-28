package com.jian.service;

import com.jian.pojo.Teacher;

public interface SignTeacherService {
    public Teacher ifTeacherExist(String username);
    public String getTeacherPwd(String username);
    public boolean insertNewTeacher(Teacher user);
}
