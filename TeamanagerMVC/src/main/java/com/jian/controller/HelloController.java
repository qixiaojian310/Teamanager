package com.jian.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jian.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT})
@Controller
//RestController不会加载视图，而是直接响应
//@RestController
public class HelloController{
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseBody
    public String hello(@RequestBody String userString) throws JsonProcessingException {
        System.out.println(userString);
        ObjectMapper mapper = new ObjectMapper();
        User user1 = mapper.readValue(userString,User.class);
        System.out.println(user1.getPassword());
        String user1JSON = mapper.writeValueAsString(user1);
        return user1JSON;
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
