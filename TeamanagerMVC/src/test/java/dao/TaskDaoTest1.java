package dao;


import com.jian.dao.TaskDao;
import com.jian.pojo.Task;
import com.jian.utils.ApplicationContextBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TaskDaoTest1 {

    private ApplicationContext ctx = ApplicationContextBuilder.getContext();
    @Test
    public void test1(){
        //执行sql，MemberDao可以对应一个MemberMapper
        TaskDao taskDao = ctx.getBean("taskDao", TaskDao.class);
        Task task = new Task();
        task.setContext("yaoyao task");
        task.setTaskName("yaoyao task");
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR,2023);
        gc.set(Calendar.MONTH,4);
        gc.set(Calendar.DAY_OF_MONTH,17);
        gc.set(Calendar.HOUR,12);
        gc.set(Calendar.MINUTE,6);
        gc.set(Calendar.SECOND,22);
        java.util.Date utilDate = new java.util.Date();
        utilDate = gc.getTime();
        Date sqlDate = new Date(utilDate.getTime());
        task.setDeadline(sqlDate);
        task.setTeamId(1);
        taskDao.addTask(task);
        System.out.println("insert task id is"+task.getTaskId());
    }


//    @Test
//    public void test2(){
//        SqlSession sqlSession = MybatisBuildUtils.getSqlSession();
//        //执行sql，MemberDao可以对应一个MemberMapper
//        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
//        Member member = memberDao.getMemberListId("12345");
//
//        System.out.println(member.toString());
//
//        sqlSession.close();
//
//    }

//    @Test
//    public void test3(){
//        SqlSession sqlSession = MybatisBuildUtils.getSqlSession();
//        //执行sql，MemberDao可以对应一个MemberMapper
//        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
//        Member member = new Member();
//        member.setMemberId("123542");
//        member.setPassword("qixiaojian");
//        member.setIsTeacher(1);
//        memberDao.addMember(member);
//        sqlSession.commit();
//        sqlSession.close();
//
//    }
//
//    public static void main(String[] args) {
//        SqlSession sqlSession = MybatisBuildUtils.getSqlSession();
//        //执行sql，MemberDao可以对应一个MemberMapper
//        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
//        List<Member> memberList = memberDao.getMemberList();
//
//        for (Member member : memberList) {
//            System.out.println(member.getMemberId());
//        }
//
//        sqlSession.close();
//    }
}
