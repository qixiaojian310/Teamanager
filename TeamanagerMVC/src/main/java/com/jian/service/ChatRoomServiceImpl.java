package com.jian.service;

import com.jian.dao.ChatDao;
import com.jian.dao.StudentDao;
import com.jian.dao.TeacherDao;
import com.jian.pojo.ChatMessage;
import com.jian.pojo.Contact;
import com.jian.pojo.Student;
import com.jian.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service("chatRoomServiceImpl")
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    @Qualifier("chatDao")
    private ChatDao chatDao;

    @Autowired
    @Qualifier("teacherDao")
    private TeacherDao teacherDao;

    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;


    @Override
    public Map<Integer, String> getChatRoomByStudentId(String studentId) {
        Map<Integer, String> chatRoomMap = new HashMap<>();
        List<Integer> chatRoomIds = chatDao.getAllChatRoomByStudentId(studentId);
        for (Integer chatRoomId : chatRoomIds) {
            String chatRoomName = chatDao.getChatRoomNameById(chatRoomId);
            chatRoomMap.put(chatRoomId, chatRoomName);
        }
        return chatRoomMap;
    }

    @Override
    public Map<Integer, String> getChatRoomByTeacherId(String teacherId) {
        Map<Integer, String> chatRoomMap = new HashMap<>();
        List<Integer> chatRoomIds = chatDao.getAllChatRoomByTeacherId(teacherId);
        for (Integer chatRoomId : chatRoomIds) {
            String chatRoomName = chatDao.getChatRoomNameById(chatRoomId);
            chatRoomMap.put(chatRoomId, chatRoomName);
        }
        return chatRoomMap;
    }


    @Override
    public List<Contact> getContacts(Integer chatRoomId) {
        List<String> contactIds = chatDao.getAllStudentsInChatRoom(chatRoomId);
        List<Contact> contacts = new LinkedList<>();
        for (String contactId : contactIds) {
            Contact contact = new Contact();
            contact.setStudent(studentDao.getStduentNoPwd(contactId));
            contact.setContactDate(chatDao.getLastChatMessage(contactId, chatRoomId).getSendTime());
            contact.setMessage(chatDao.getLastChatMessage(contactId, chatRoomId).getMessage());
            contacts.add(contact);
        }
        return contacts;
    }

    @Override
    public Teacher getTeacher(Integer chatRoomId) {
        String teacherId = chatDao.getTeacherInChatRoom(chatRoomId);
        return teacherDao.getTeacher(teacherId);
    }

    @Override
    public boolean addStudentMessage(ChatMessage message, Integer chatRoomId) {
        Integer state = chatDao.addStudentChatMessage(message.getSenderId(), chatRoomId, message.getMessage(), message.getSendTime());
        if (state == 1) {
            return true;
        } else if (state == 0) {
            return false;
        }
        return false;
    }

    @Override
    public boolean addTeacherMessage(String teacherId, String messageContent, Integer chatRoomId, Date sendTime) {
        Integer state = chatDao.addTeacherChatMessage(teacherId, chatRoomId, messageContent, sendTime);
        if (state == 1) {
            return true;
        } else if (state == 0) {
            return false;
        }
        return false;
    }

    @Override
    public List<ChatMessage> getAllMessage(Integer chatRoomId) {
        List<ChatMessage> studentChatMessages = chatDao.getStudentChatMessageByChatRoomId(chatRoomId);
        List<ChatMessage> teacherChatMessages = chatDao.getTeacherChatMessageByChatRoomId(chatRoomId);
        for (ChatMessage teacherChatMessage : teacherChatMessages) {
            teacherChatMessage.setStudentState(false);
        }
        for (ChatMessage studentChatMessage : studentChatMessages) {
            studentChatMessage.setStudentState(true);
        }
        studentChatMessages.addAll(teacherChatMessages);
        Collections.sort(studentChatMessages, new Comparator<ChatMessage>() {
            @Override
            //定义一个比较器
            public int compare(ChatMessage message1, ChatMessage message2) {
                try {
                    Date dt1 = message1.getSendTime();
                    Date dt2 = message2.getSendTime();
                    if (dt1.getTime() > dt2.getTime()) {
                        return 1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return studentChatMessages;
    }


}
