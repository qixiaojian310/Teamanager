package com.jian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jian.pojo.Student;
import com.jian.service.SignStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT},allowCredentials = "true")
@Controller
public class SignupController {


    @Autowired
    @Qualifier("signinStudentServiceImpl")
    private SignStudentService memberService;

    @RequestMapping(value = "/registerStudent", method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(@RequestBody Student userString) throws JsonProcessingException {
        if (memberService.ifStudentExist(userString.getStudentId())!=null) {
            return "already have user";
        } else {
            if (memberService.insertNewStudent(userString)) return "success";
            else return "failed !!!";
        }

    }
}
