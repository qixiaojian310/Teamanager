package dao;


import com.jian.dao.MemberDao;
import com.jian.pojo.Member;
import com.jian.utils.MybatisBuildUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

public class DaoTest1 {

    static Logger logger = Logger.getLogger(DaoTest1.class);
    @Test
    public void test1(){
        SqlSession sqlSession = MybatisBuildUtils.getSqlSession();
        //执行sql，MemberDao可以对应一个MemberMapper
        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
        List<Member> memberList = memberDao.getMemberList();

        for (Member member : memberList) {
            System.out.println(member.toString());
        }

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
