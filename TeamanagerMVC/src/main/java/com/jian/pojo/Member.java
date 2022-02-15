package com.jian.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;


@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    private String memberId;
    private String password;
    private Integer isTeacher;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer isTeacher() {
        return isTeacher;
    }

    public void setTeacher(Integer teacher) {
        isTeacher = teacher;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                ", isTeacher=" + isTeacher +
                '}';
    }
}
