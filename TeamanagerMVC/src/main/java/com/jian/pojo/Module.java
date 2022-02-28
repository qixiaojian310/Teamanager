package com.jian.pojo;

import java.util.List;

public class Module {
    private Integer moduleId;
    private String moduleName;
    private Teacher teacher;
    private List<Student> studentList;
    private List<Integer> teamIdList;
    private Integer moduleSize;


    public Integer getModuleSize() {
        return moduleSize;
    }

    public void setModuleSize(Integer moduleSize) {
        this.moduleSize = moduleSize;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Integer> getTeamIdList() {
        return teamIdList;
    }

    public void setTeamIdList(List<Integer> teamIdList) {
        this.teamIdList = teamIdList;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", teacher=" + teacher +
                ", studentList=" + studentList +
                ", teamIdList=" + teamIdList +
                ", moduleSize=" + moduleSize +
                '}';
    }
}
