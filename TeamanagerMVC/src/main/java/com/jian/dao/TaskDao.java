package com.jian.dao;


import com.jian.pojo.Student;
import com.jian.pojo.SubTask;
import com.jian.pojo.Task;


import java.util.List;

public interface TaskDao {
    //这个地方只能获取除了组员之外的task信息
    public Task getTask(int taskId);
    //获取该task的组员信息
    public List<Student> getStudentList(int taskId);
    //增加一个空的任务 返回该任务的taskid
    public int addTask(Task task);
    //给该任务添加学生
    public boolean addTaskStudent(int taskId,String studentId);
    //跟据任务和学生id得到该学生的成绩
    public Integer getTaskProgress(int taskId,String studentId);
    //得到当前任务平均进度
    public Integer getTaskProgressAvg(int taskId);
    //判断当前学生是否有该任务
    public Integer ensureStudentJoinTask(int taskId,String studentId);
    //新建一个子任务
    public Integer createSubTask(SubTask subTask);
    //获取某个任务下的所有子任务
    public List<SubTask> getAllSubTaskInTask(Integer taskId);
    //删除子任务
    public boolean deleteSubTask(Integer subTaskId);
    //完成子任务
    public boolean finishSubTask(Integer subTaskId);
    //完成一个任务
    public boolean finishTask(Integer taskId);
    //让任务不再完成
    public boolean cancelFinishTask(Integer taskId);
    //获取一个task的storypoint
    public Integer getStoryPoint(Integer taskId);
    //老师打分
    public Integer changeScore(Integer teamId, Integer score, String studentId);
    //获取分数
    public Integer getScore(Integer teamId, String studentId);
}
