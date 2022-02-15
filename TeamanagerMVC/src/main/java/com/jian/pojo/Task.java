package com.jian.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;


@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private String taskid;

    private String teamid;
    private Date deadline;
    private String context;
    private List<Team> teamList;

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }



    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }



    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
