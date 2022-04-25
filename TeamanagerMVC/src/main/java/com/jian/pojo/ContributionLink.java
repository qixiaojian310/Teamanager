package com.jian.pojo;

public class ContributionLink {
    private Integer taskId;
    private Integer storyPoint;

    public ContributionLink(Integer taskId, Integer storyPoint) {
        this.taskId = taskId;
        this.storyPoint = storyPoint;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(Integer storyPoint) {
        this.storyPoint = storyPoint;
    }
}
