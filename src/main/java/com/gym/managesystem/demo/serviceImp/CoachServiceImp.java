package com.gym.managesystem.demo.serviceImp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.gym.managesystem.demo.bean.Coach;
import com.gym.managesystem.demo.bean.CoachExample;
import com.gym.managesystem.demo.service.CoachService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CoachService")
public class CoachServiceImp implements CoachService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private PageInterceptor pageInterceptor;
    @Override
    public int addCoach(Coach Coach) {
        int i = sqlSessionTemplate.insert("com.gym.managesystem.demo.mapper.CoachMapper.insert",Coach);
        return i;
    }

    @Override
    public int deleteCoach(CoachExample example) {
        int i  = sqlSessionTemplate.delete("com.gym.managesystem.demo.mapper.CoachMapper.deleteByExample",example);
        return i;
    }

    @Override
    public int updateCoach(Coach Coach, CoachExample example) {

        return 0;
    }

    @Override
    public Page<Coach> CoachList(CoachExample example, int pageNum, int pageSize) {

        Page<Coach> page =  PageHelper.startPage(pageNum, pageSize);
         sqlSessionTemplate.selectList("com.gym.managesystem.demo.mapper.CoachMapper.selectByExample",example);
        return page;
    }


}
