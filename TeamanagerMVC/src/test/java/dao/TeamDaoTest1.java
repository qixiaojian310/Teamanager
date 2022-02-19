package dao;


import com.jian.dao.TeamDao;
import com.jian.utils.ApplicationContextBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class TeamDaoTest1 {
    private ApplicationContext ctx = ApplicationContextBuilder.getContext();
    @Test
    public void test1(){
        TeamDao teamDao = ctx.getBean("teamDao", TeamDao.class);
        String studentId = "1234";
        Integer teamId = 1;
        System.out.println(teamDao.getTask(teamId,studentId));
    }
}
