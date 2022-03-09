package com.jian.pojo;

import java.util.List;

public class Module {
    private Integer moduleId;
    private String moduleName;
    private Teacher teacher;
    private List<Student> students;
    private List<Integer> teamIds;
    private Integer moduleSize;
    private Integer teamNum;

    public Integer getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
    }

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Integer> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Integer> teamIds) {
        this.teamIds = teamIds;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", teacher=" + teacher +
                ", students=" + students +
                ", teamIds=" + teamIds +
                ", moduleSize=" + moduleSize +
                '}';
    }
}
