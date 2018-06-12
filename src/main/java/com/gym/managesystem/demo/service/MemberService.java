package com.gym.managesystem.demo.service;

import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Member;
import com.gym.managesystem.demo.bean.MemberExample;

import java.util.List;

public interface MemberService {

    int addMember(Member member);

    int deleteMember(MemberExample example);

    int updateMember(Member member,MemberExample example);

    Page<Member> memberList(MemberExample example, int pageNum, int pageSize);
}
