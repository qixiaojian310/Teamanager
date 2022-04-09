package com.jian.pojo;

import java.security.Principal;
import java.util.List;

public class ChatRoom {
    private Integer chatRoomId;
    private String chatRoomName;
    private Teacher teacher;
    private List<Student> students;
    private List<ChatMessage> messages;

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer charRoomId) {
        this.chatRoomId = charRoomId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }
}
