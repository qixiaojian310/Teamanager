package com.jian.service;

import com.jian.dao.MemberDao;
import com.jian.pojo.Member;
import org.springframework.stereotype.Service;

@Service("signinMemberServiceImpl")
public class SigninMemberServiceImpl implements MemberService {

    private MemberDao memberDao;
    @Override
    public boolean ifUserExist(String username) {

        if(memberDao.ifUserExist(username).isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public String getUserPwd(String username) {
        return null;
    }

    @Override
    public boolean insertNewUser(Member user){return true;}
}
