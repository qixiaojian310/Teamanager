package com.jian.interceptors;

import com.jian.pojo.ChatRoom;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.LinkedList;
import java.util.Map;


@Component
public class GetHeaderParamInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        //1、判断是否首次连接
        if (StompCommand.CONNECT.equals(accessor.getCommand())){
            //2、判断用户名和密码
            String chatRoomIdStr = accessor.getNativeHeader("chatRoomId").get(0);
            Principal principal = new Principal() {
                @Override
                public String getName() {
                    return chatRoomIdStr;
                }
            };
            accessor.setUser(principal);
            return message;
        }
        //不是首次连接，已经登陆成功
        return message;
    }
}
