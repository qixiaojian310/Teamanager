package com.jian.service;

import com.jian.pojo.ContributionMap;
import com.jian.pojo.Student;

import java.util.List;
import java.util.Map;

public interface TaskService {
    public List<ContributionMap> getStudentContribution(Integer teamId);
    public Integer getAllStoryPoint(Integer teamId);
    public Integer changeScore(Integer teamId,Integer score,String studentId);
}
