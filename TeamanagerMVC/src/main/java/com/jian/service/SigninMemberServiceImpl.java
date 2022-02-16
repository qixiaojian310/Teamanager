package com.jian.service;

import com.jian.dao.MemberDao;
import com.jian.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("signinMemberServiceImpl")
public class SigninMemberServiceImpl implements MemberService {


    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;
    @Override
    public boolean ifUserExist(String username) {

        System.out.println(memberDao.getMemberListId("12345"));
        return false;
    }

    @Override
    public String getUserPwd(String username) {
        return null;
    }

    @Override
    public boolean insertNewUser(Member user){return true;}
}
