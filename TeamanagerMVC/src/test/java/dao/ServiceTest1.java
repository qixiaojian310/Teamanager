package dao;

import com.jian.dao.MemberDao;
import com.jian.pojo.Member;
import com.jian.service.MemberService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ServiceTest1 {
    private ApplicationContext ctx;
    @Test
    public void test1(){
        ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        SqlSession sqlSession = (SqlSession) ctx.getBean("sqlSession");
        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
        List<Member> memberList = memberDao.getMemberList();
        for (Member member : memberList) {
            System.out.println(member);
        }
    }
}
