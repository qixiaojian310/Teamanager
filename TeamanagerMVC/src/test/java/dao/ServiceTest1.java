package dao;

import com.jian.service.MemberService;
import com.jian.utils.ApplicationContextBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class ServiceTest1 {
    @Test
    public void test1(){
        ApplicationContext ctx = ApplicationContextBuilder.getContext();
        MemberService memberService = ctx.getBean("signinMemberServiceImpl",MemberService.class);

    }
}
