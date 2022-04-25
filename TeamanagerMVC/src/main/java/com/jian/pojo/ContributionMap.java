package com.jian.pojo;

import java.util.List;
import java.util.Map;

public class ContributionMap {
    private Student student;
    private List<ContributionLink> contributionStoryPoints;
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<ContributionLink> getContributionStoryPoints() {
        return contributionStoryPoints;
    }

    public void setContributionStoryPoints(List<ContributionLink> contributionStoryPoints) {
        this.contributionStoryPoints = contributionStoryPoints;
    }
}
