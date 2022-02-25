package dao;

import com.jian.dao.StudentDao;
import com.jian.dao.TaskDao;
import com.jian.pojo.Student;
import com.jian.pojo.Task;
import com.jian.utils.ApplicationContextBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StudentDaoTest1 {
    private ApplicationContext ctx = ApplicationContextBuilder.getContext();
    @Test
    public void test1(){
        //执行sql，MemberDao可以对应一个MemberMapper
        StudentDao studentDao = ctx.getBean("studentDao",StudentDao.class);
        Student student = studentDao.getStudent("1234");
        System.out.println(student);
    }
}
