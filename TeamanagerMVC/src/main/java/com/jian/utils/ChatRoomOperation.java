package com.jian.utils;


//该类表示正在操作的聊天室
public class ChatRoomOperation {
    private static Integer chatRoomId;

    public static Integer getChatRoomId() {
        return chatRoomId;
    }

    public static void setChatRoomId(Integer chatRoomId) {
        ChatRoomOperation.chatRoomId = chatRoomId;
    }
}
