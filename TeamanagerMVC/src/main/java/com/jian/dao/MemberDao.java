package com.jian.dao;

import com.jian.pojo.Member;

import java.util.List;

public interface MemberDao {
    public List<Member> getMemberList();
    public Member getMemberListId(String memberId);
    public Integer addMember(Member member);

    public String getUserPwd(String memberId);
}
