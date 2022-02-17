package dao;

import com.jian.service.StudentService;
import com.jian.utils.ApplicationContextBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class ServiceTest1 {
    @Test
    public void test1(){
        ApplicationContext ctx = ApplicationContextBuilder.getContext();
        StudentService memberService = ctx.getBean("signinMemberServiceImpl",StudentService.class);

    }
}
