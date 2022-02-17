package com.jian.service;

import com.jian.pojo.Student;

public interface StudentService {
    public boolean ifStudentExist(String username);
    public String getStudentPwd(String username);
    public boolean insertNewStudent(Student user);
}
