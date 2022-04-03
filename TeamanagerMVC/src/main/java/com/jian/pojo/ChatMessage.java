package com.jian.pojo;


public class ChatMessage {

    private String sendName;
    private String message;
    private String receiveName;

    public ChatMessage() {
    }

    public ChatMessage(String sendName, String message, String receiveName) {
        this.sendName = sendName;
        this.message = message;
        this.receiveName = receiveName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}
