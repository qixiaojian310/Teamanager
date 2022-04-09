package com.jian.pojo;


import java.util.Date;

public class ChatMessage {

    private String senderId;
    private boolean studentState;
    private String message;
    private Date sendTime;


    public ChatMessage() {
    }

    public ChatMessage(String senderId, boolean studentState, String message, Date sendTime) {
        this.senderId = senderId;
        this.studentState = studentState;
        this.message = message;
        this.sendTime = sendTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }


    public boolean isStudentState() {
        return studentState;
    }

    public void setStudentState(boolean studentState) {
        this.studentState = studentState;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
