package com.jian.service;

import com.jian.pojo.ChatMessage;
import com.jian.pojo.Contact;
import com.jian.pojo.Teacher;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ChatRoomService {
    //通过学生Id获取聊天室Id和名字对应的Map
    public Map<Integer, String> getChatRoomByStudentId(String studentId);
    //通过老师Id获取聊天室Id和名字对应的Map
    public Map<Integer, String> getChatRoomByTeacherId(String teacherId);
    //获取联系人
    public List<Contact> getContacts(Integer chatRoomId);
    //获取老师
    public Teacher getTeacher(Integer chatRoomId);
    //添加一条学生聊天记录
    public boolean addStudentMessage(ChatMessage message,Integer chatRoomId);
    //添加一条老师聊天记录
    public boolean addTeacherMessage(String teacherId, String messageContent, Integer chatRoomId, Date sendTime);
    //获取所有的聊天记录
    public List<ChatMessage> getAllMessage(Integer chatRoomId);

    public Integer joinChatRoom(String studentId, Integer chatRoomId);
}
