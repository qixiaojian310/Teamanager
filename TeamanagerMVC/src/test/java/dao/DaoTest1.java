package dao;


import com.jian.dao.MemberDao;
import com.jian.pojo.Member;
import com.jian.utils.ApplicationContextBuilder;
import com.jian.utils.MybatisBuildUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class DaoTest1 {

    private ApplicationContext ctx = ApplicationContextBuilder.getContext();
    static Logger logger = Logger.getLogger(DaoTest1.class);
    @Test
    public void test1(){
        //执行sql，MemberDao可以对应一个MemberMapper
        MemberDao memberDao = ctx.getBean("memberDao",MemberDao.class);
        List<Member> memberList = memberDao.getMemberList();

        for (Member member : memberList) {
            System.out.println(member.toString());
        }

    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisBuildUtils.getSqlSession();
        //执行sql，MemberDao可以对应一个MemberMapper
        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
        Member member = memberDao.getMemberListId("12345");

        System.out.println(member.toString());

        sqlSession.close();

    }

    @Test
    public void test3(){
        SqlSession sqlSession = MybatisBuildUtils.getSqlSession();
        //执行sql，MemberDao可以对应一个MemberMapper
        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
        Member member = new Member();
        member.setMemberId("123542");
        member.setPassword("qixiaojian");
        member.setIsTeacher(1);
        memberDao.addMember(member);
        sqlSession.commit();
        sqlSession.close();

    }

    public static void main(String[] args) {
        SqlSession sqlSession = MybatisBuildUtils.getSqlSession();
        //执行sql，MemberDao可以对应一个MemberMapper
        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
        List<Member> memberList = memberDao.getMemberList();

        for (Member member : memberList) {
            System.out.println(member.getMemberId());
        }

        sqlSession.close();
    }
}
