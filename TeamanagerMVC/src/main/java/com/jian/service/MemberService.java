package com.jian.service;

import com.jian.pojo.Member;

public interface MemberService {
    public boolean ifUserExist(String username);
    public String getUserPwd(String username);
    public int insertNewUser(Member user);
}
