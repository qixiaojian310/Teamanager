package com.jian.dao;

import com.jian.pojo.Member;

import java.util.List;

public interface MemberDao {
    public List<Member> getMemberList();
    public Member getMemberListId(String id);
    public int addMember(Member member);
    public List<Member> ifUserExist(String memberId);

    public String getUserPwd(String memberId);
}
