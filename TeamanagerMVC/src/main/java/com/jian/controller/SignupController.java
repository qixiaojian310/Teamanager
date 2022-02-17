package com.jian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jian.pojo.Student;
import com.jian.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST})
@Controller
public class SignupController {


    @Autowired
    @Qualifier("signinStudentServiceImpl")
    private StudentService memberService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public boolean hello2(@RequestBody Student userString) throws JsonProcessingException {
        if (memberService.ifStudentExist(userString.getStudentId())){
            return false;
        }
        else{
            if(memberService.insertNewStudent(userString))return true;
            else return false;
        }

    }
}
