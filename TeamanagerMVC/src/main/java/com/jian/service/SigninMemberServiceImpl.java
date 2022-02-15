package com.jian.service;

import com.jian.pojo.Member;
import org.springframework.stereotype.Service;

@Service("signinMemberServiceImpl")
public class SigninMemberServiceImpl implements MemberService {
    @Override
    public boolean ifUserExist(String username) {
        return false;
    }

    @Override
    public String getUserPwd(String username) {
        return null;
    }

    @Override
    public boolean insertNewUser(Member user){return true;}
}
