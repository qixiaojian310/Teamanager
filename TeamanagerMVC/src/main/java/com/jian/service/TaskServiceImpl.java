package com.jian.service;


import com.jian.dao.StudentDao;
import com.jian.dao.TaskDao;
import com.jian.dao.TeamDao;
import com.jian.pojo.ContributionLink;
import com.jian.pojo.ContributionMap;
import com.jian.pojo.Student;
import com.jian.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    @Qualifier("teamDao")
    private TeamDao teamDao;

    @Autowired
    @Qualifier("taskDao")
    private TaskDao taskDao;

    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    //寻找所有已经完成的task
    @Override
    public List<ContributionMap> getStudentContribution(Integer teamId) {
        List<String> teamMemberIds = teamDao.getTeammate(teamId);
        List<ContributionMap> contributionMaps = new LinkedList<>();
        for (String teamMemberId : teamMemberIds) {
            ContributionMap contributionMap = new ContributionMap();
            List<Integer> taskIds = teamDao.getAllTaskByStudent(teamMemberId, teamId,true);
            List<ContributionLink> storyPointSum = new LinkedList<>();
            for (Integer taskId : taskIds) {
                Integer storyPointSep = taskDao.getStoryPoint(taskId);
                ContributionLink contributionLink = new ContributionLink(taskId, storyPointSep);
                storyPointSum.add(contributionLink);
            }
            Student student = studentDao.getStduentNoPwd(teamMemberId);
            contributionMap.setContributionStoryPoints(storyPointSum);
            contributionMap.setStudent(student);
            contributionMap.setScore(taskDao.getScore(teamId, teamMemberId));
            contributionMaps.add(contributionMap);

        }
        return contributionMaps;
    }

    @Override
    public Integer getAllStoryPoint(Integer teamId) {
        List<String> teamMemberIds = teamDao.getTeammate(teamId);
        Integer storyPointSum = 0;
        for (String teamMemberId : teamMemberIds) {
            List<Integer> taskIds = teamDao.getAllTaskByStudent(teamMemberId, teamId,true);
            for (Integer taskId : taskIds) {
                Integer storyPointSep = taskDao.getStoryPoint(taskId);
                storyPointSum += storyPointSep;
            }
        }
        return storyPointSum;
    }

    @Override
    public Integer changeScore(Integer teamId, Integer score, String studentId) {
        return taskDao.changeScore(teamId, score, studentId);
    }
}
