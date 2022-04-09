package com.jian.pojo;

public class SubscibeMessage {
    private ChatMessage chatMessage;
    private Integer chatRoomId;

    public SubscibeMessage(ChatMessage chatMessage, Integer chatRoomId) {
        this.chatMessage = chatMessage;
        this.chatRoomId = chatRoomId;
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}
