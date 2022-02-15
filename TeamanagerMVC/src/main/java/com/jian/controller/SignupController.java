package com.jian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jian.service.MemberService;
import com.jian.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST})
@Controller
public class SignupController {


    @Autowired
    @Qualifier("signinMemberServiceImpl")
    private MemberService userDao;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public boolean hello2(@RequestBody Member userString) throws JsonProcessingException {
        if (userDao.ifUserExist(userString.getMemberId())){
            return false;
        }
        else{
            return userDao.insertNewUser(userString);
        }

    }
}
