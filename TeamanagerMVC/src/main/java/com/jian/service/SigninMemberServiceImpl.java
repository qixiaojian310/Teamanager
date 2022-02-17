package com.jian.service;

import com.jian.dao.MemberDao;
import com.jian.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("signinMemberServiceImpl")
public class SigninMemberServiceImpl implements MemberService {


    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public boolean ifUserExist(String username) {

        List<Member> memberList = memberDao.getMemberList();

        for (Member member : memberList) {
            System.out.println(member);
        }
        return false;
    }

    @Override
    public String getUserPwd(String username) {

        return memberDao.getUserPwd(username);
    }

    @Override
    public int insertNewUser(Member user){
        return memberDao.addMember(user);
    }
}
