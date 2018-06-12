package com.gym.managesystem.demo.serviceImp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.gym.managesystem.demo.bean.Feedback;
import com.gym.managesystem.demo.bean.FeedbackExample;
import com.gym.managesystem.demo.service.FeedbackService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("FeedbackService")
public class FeedbackServiceImp implements FeedbackService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private PageInterceptor pageInterceptor;
    @Override
    public int addFeedback(Feedback Feedback) {
        int i = sqlSessionTemplate.insert("com.gym.managesystem.demo.mapper.FeedbackMapper.insert",Feedback);
        return i;
    }

    @Override
    public int deleteFeedback(FeedbackExample example) {
        int i  = sqlSessionTemplate.delete("com.gym.managesystem.demo.mapper.FeedbackMapper.deleteByExample",example);
        return i;
    }

    @Override
    public int updateFeedback(Feedback Feedback, FeedbackExample example) {

        return 0;
    }

    @Override
    public Page<Feedback> FeedbackList(FeedbackExample example, int pageNum, int pageSize) {

        Page<Feedback> page =  PageHelper.startPage(pageNum, pageSize);
         sqlSessionTemplate.selectList("com.gym.managesystem.demo.mapper.FeedbackMapper.selectByExample",example);
        return page;
    }


}
