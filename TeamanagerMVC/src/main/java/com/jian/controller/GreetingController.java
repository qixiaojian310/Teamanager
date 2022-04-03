package com.jian.controller;

import com.jian.websocket.test.Greeting;
import com.jian.pojo.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public boolean sendMessage(ChatMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay

        return true;
    }

}