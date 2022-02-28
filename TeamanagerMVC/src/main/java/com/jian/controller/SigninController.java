package com.jian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jian.pojo.Student;
import com.jian.pojo.Teacher;
import com.jian.service.SignStudentService;
import com.jian.service.SignTeacherService;
import com.jian.service.StudentShowService;
import com.jian.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT},allowCredentials = "true")
@Controller
//RestController不会加载视图，而是直接响应
//@RestController
public class SigninController {


    @Autowired
    @Qualifier("studentShowServiceImpl")
    private StudentShowService studentshowService;

    @Autowired
    @Qualifier("signinStudentServiceImpl")
    private SignStudentService signStudentService;

    @Autowired
    @Qualifier("signTeacherServiceImpl")
    private SignTeacherService signTeacherService;

    @RequestMapping(value = "/hello2", method = RequestMethod.POST)
    @ResponseBody
    public String hello(@RequestBody String userString) throws JsonProcessingException {
        System.out.println(userString);
        return userString;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String hello2(@RequestBody Student userString) throws JsonProcessingException {
        System.out.println(userString.getPassword());
        System.out.println("查询的用户名为"+userString);
        Student loginStudent = signStudentService.ifStudentExist(userString.getStudentId());
        if (loginStudent!=null){
            if(userString.getPassword().equals(signStudentService.getStudentPwd(userString.getStudentId()))){
                HttpSession session = GetSessionUtil.getSession();
                System.out.println(session);
                session.setAttribute("loginStudent",loginStudent);
                Student studentTest = (Student) session.getAttribute("loginStudent");
                System.out.println("session的测试"+studentTest.getStudentInfo());
                return "Match";
            }
            else return "NoMatch";
        }
        else{
            return "NoExit";
        }

    }


    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    @ResponseBody
    public String hello3(@RequestBody Teacher userString) throws JsonProcessingException {
        System.out.println(userString.getPassword());
        System.out.println("查询的用户名为"+userString);
        Teacher loginTeacher = signTeacherService.ifTeacherExist(userString.getTeacherId());
        if (loginTeacher!=null){
            if(userString.getPassword().equals(signTeacherService.getTeacherPwd(userString.getTeacherId()))){
                HttpSession session = GetSessionUtil.getSession();
                System.out.println(session);
                session.setAttribute("loginTeacher",loginTeacher);
                Teacher teacherTest = (Teacher) session.getAttribute("loginTeacher");
                System.out.println("session的测试"+teacherTest.toString());
                return "Match";
            }
            else return "NoMatch";
        }
        else{
            return "NoExit";
        }

    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestBody String id){
        System.out.println(id);
        return id.toString();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String testGet( Integer id){
        System.out.println(id);
        return id.toString();
    }
}
