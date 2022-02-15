package com.jian.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;


@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
    private String teamid;
    private String leaderid;
    private String moduleid;

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getLeaderid() {
        return leaderid;
    }

    public void setLeaderid(String leaderid) {
        this.leaderid = leaderid;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }


}
