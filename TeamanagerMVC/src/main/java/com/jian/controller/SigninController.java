package com.jian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jian.service.MemberService;
import com.jian.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT})
@Controller
//RestController不会加载视图，而是直接响应
//@RestController
public class SigninController {

    @Autowired
    @Qualifier("signinUserDaoImpl")
    private MemberService userDao;


    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseBody
    public String hello(@RequestBody String userString) throws JsonProcessingException {
        System.out.println(userString);
        ObjectMapper mapper = new ObjectMapper();
        Member user1 = mapper.readValue(userString, Member.class);
        System.out.println(user1.getPassword());
        String user1JSON = mapper.writeValueAsString(user1);
        return user1JSON;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.POST)
    @ResponseBody
    public String hello2(@RequestBody Member userString) throws JsonProcessingException {
        System.out.println(userString.getPassword());
        if (userDao.ifUserExist(userString.getMemberId())){
            if(userString.getPassword().equals(userDao.getUserPwd(userString.getMemberId()))){
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
