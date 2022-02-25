package dao;

import com.jian.service.SignStudentService;
import com.jian.utils.ApplicationContextBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class ServiceTest1 {
    @Test
    public void test1(){
        ApplicationContext ctx = ApplicationContextBuilder.getContext();
        SignStudentService memberService = ctx.getBean("signinMemberServiceImpl", SignStudentService.class);

    }
}
