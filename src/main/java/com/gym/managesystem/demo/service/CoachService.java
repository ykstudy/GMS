package com.gym.managesystem.demo.service;

import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Coach;
import com.gym.managesystem.demo.bean.CoachExample;

public interface CoachService {

    int addCoach(Coach Coach);

    int deleteCoach(CoachExample example);

    int updateCoach(Coach Coach, CoachExample example);

    Page<Coach> CoachList(CoachExample example, int pageNum, int pageSize);
}
