package com.jian.pojo;

public class Student {

    private String studentId;
    private String studentInfo;
    private String password;
    private String userIconSrc;

    public String getUserIconSrc() {
        return userIconSrc;
    }

    public void setUserIconSrc(String userIconSrc) {
        this.userIconSrc = userIconSrc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(String studentInfo) {
        this.studentInfo = studentInfo;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentid) {
        this.studentId = studentid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentInfo='" + studentInfo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
