package com.jian.dao;

import com.jian.pojo.ChatMessage;
import com.jian.pojo.ChatRoom;
import com.jian.pojo.Teacher;

import java.util.Date;
import java.util.List;

public interface ChatDao {
    //获取一个学生所拥有的所有聊天室Id
    public List<Integer> getAllChatRoomByStudentId(String studentId);
    //获取老师的聊天室Id
    public List<Integer> getAllChatRoomByTeacherId(String teacherId);
    //通过聊天室id获取聊天室的名字
    public String getChatRoomNameById(Integer chatRoomId);
    //通过聊天室ID获取聊天室的学生消息列表
    public List<ChatMessage> getStudentChatMessageByChatRoomId(Integer chatRoomId);
    //通过聊天室ID获取聊天室的老师聊天列表
    public List<ChatMessage> getTeacherChatMessageByChatRoomId(Integer chatRoomId);
    //获取聊天室的所有学生id
    public List<String> getAllStudentsInChatRoom(Integer chatRoomId);
    //获取聊天室的老师id
    public String getTeacherInChatRoom(Integer chatRoomId);
    //通过ChatMessage来新增一条学生记录
    public Integer addStudentChatMessage(String studentId, Integer chatRoomId, String message, Date sendTime);
    //通过ChatMessage来新增一条老师记录
    public Integer addTeacherChatMessage(String teacherId, Integer chatRoomId, String message, Date sendTime);
    //新增一个学生到chatroom中
    public Integer addStudentToChatRoom(String studentId, Integer chatRoomId);
    //查询用户在某个聊天室下的linkId
    public Integer getLinkIdInChatRoom(String studentId,Integer chatRoomId);
    //查询某个学生最后的聊天记录内容
    public ChatMessage getLastChatMessage(String studentId,Integer chatRoomId);
    //
    public Integer addChatRoom(ChatRoom chatRoom);
}
