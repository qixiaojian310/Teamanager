package com.jian.controller;


import com.jian.pojo.*;
import com.jian.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT},allowCredentials = "true")
@Controller
public class ChatRoomController {

    @Autowired
    @Qualifier("chatRoomServiceImpl")
    private ChatRoomService chatRoomService;

    //一个学生获取他所有的聊天室
    @RequestMapping("/student/getChatRoom")
    @ResponseBody
    public Map<Integer,String> getStudentChatRoom(@RequestParam("studentId") String studentId){
       return chatRoomService.getChatRoomByStudentId(studentId);
    }

    //一个老师获取他所有的聊天室
    @RequestMapping("/teacher/getChatRoom")
    @ResponseBody
    public Map<Integer,String> getTeacherChatRoom(@RequestParam("teacherId") String teacherId){
        return chatRoomService.getChatRoomByTeacherId(teacherId);
    }

    //获取所有的联系人
    @RequestMapping("/getContact")
    @ResponseBody
    public List<Contact> getContact(@RequestParam("chatRoomId") Integer chatRoomId){
        return chatRoomService.getContacts(chatRoomId);
    }

    //获取老师
    @RequestMapping("/getTeacher")
    @ResponseBody
    public Teacher getTeacher(@RequestParam("chatRoomId") Integer chatRoomId){
        return chatRoomService.getTeacher(chatRoomId);
    }

    //获取所有现有的聊天记录
    @RequestMapping("/getMessages")
    @ResponseBody
    public List<ChatMessage> getMessage(@RequestParam("chatRoomId") Integer chatRoomId){
        List<ChatMessage> chatMessages = chatRoomService.getAllMessage(chatRoomId);
        return chatMessages;
    }

    //加入聊天室
    @RequestMapping("/joinChatRoom")
    @ResponseBody
    public Integer joinChatRoom(@RequestParam("studentId") String studentId,@RequestParam("chatRoomId") Integer chatRoomId){
        return chatRoomService.joinChatRoom(studentId,chatRoomId);
    }

}
