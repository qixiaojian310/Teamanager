package dao;

import com.jian.dao.MemberDao;
import com.jian.pojo.Member;
import com.jian.service.MemberService;
import com.jian.utils.ApplicationContextBuilder;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ServiceTest1 {
    @Test
    public void test1(){
        ApplicationContext ctx = ApplicationContextBuilder.getContext();
        MemberService memberService = ctx.getBean("signinMemberServiceImpl",MemberService.class);
        memberService.ifUserExist("das");

    }
}
