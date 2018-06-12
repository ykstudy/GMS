package com.gym.managesystem.demo.service;

import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Feedback;
import com.gym.managesystem.demo.bean.FeedbackExample;

public interface FeedbackService {

    int addFeedback(Feedback Feedback);

    int deleteFeedback(FeedbackExample example);

    int updateFeedback(Feedback Feedback, FeedbackExample example);

    Page<Feedback> FeedbackList(FeedbackExample example, int pageNum, int pageSize);
}
