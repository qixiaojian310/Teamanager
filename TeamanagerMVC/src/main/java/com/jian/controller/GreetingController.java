package com.jian.controller;

import com.jian.pojo.SubscibeMessage;
import com.jian.service.ChatRoomService;
import com.jian.utils.ChatRoomOperation;
import com.jian.websocket.test.Greeting;
import com.jian.pojo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class GreetingController {


    @Autowired
    @Qualifier("chatRoomServiceImpl")
    private ChatRoomService chatRoomService;



    @MessageMapping("/sendMessage")
    @SendToUser("/queue/receiveMessage")
    public SubscibeMessage sendMessage(Principal principal, @RequestBody ChatMessage message) throws Exception {

        Integer chatRoomId = Integer.valueOf(principal.getName());
        //设置空的消息
        ChatMessage emptyMessage = new ChatMessage();

        //先获取需要操作的聊天室ID
        boolean state = false;
        if(message.isStudentState()){
            System.out.println("学生发送消息");
            state = chatRoomService.addStudentMessage(message,chatRoomId);
        }else {
            System.out.println("老师发送消息");
            state = chatRoomService.addTeacherMessage(message.getSenderId(),message.getMessage(),chatRoomId,message.getSendTime());
        }
        if(state) {
            return new SubscibeMessage(message,chatRoomId);
        }else {
            return new SubscibeMessage(emptyMessage,chatRoomId);
        }
    }

}