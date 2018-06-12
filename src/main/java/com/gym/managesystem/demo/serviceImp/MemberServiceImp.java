package com.gym.managesystem.demo.serviceImp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.gym.managesystem.demo.bean.Member;
import com.gym.managesystem.demo.bean.MemberExample;
import com.gym.managesystem.demo.service.MemberService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("memberService")
public class MemberServiceImp implements MemberService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private PageInterceptor pageInterceptor;
    @Override
    public int addMember(Member member) {
        int i = sqlSessionTemplate.insert("com.gym.managesystem.demo.mapper.MemberMapper.insert",member);
        return i;
    }

    @Override
    public int deleteMember(MemberExample example) {
        int i  = sqlSessionTemplate.delete("com.gym.managesystem.demo.mapper.MemberMapper.deleteByExample",example);
        return i;
    }

    @Override
    public int updateMember(Member member, MemberExample example) {

        return 0;
    }

    @Override
    public Page<Member> memberList(MemberExample example, int pageNum, int pageSize) {

        Page<Member> page =  PageHelper.startPage(pageNum, pageSize);
         sqlSessionTemplate.selectList("com.gym.managesystem.demo.mapper.MemberMapper.selectByExample",example);
        return page;
    }


}
