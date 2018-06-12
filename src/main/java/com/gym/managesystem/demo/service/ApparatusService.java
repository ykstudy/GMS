package com.gym.managesystem.demo.service;

import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Apparatus;
import com.gym.managesystem.demo.bean.ApparatusExample;

public interface ApparatusService {

    int addApparatus(Apparatus Apparatus);

    int deleteApparatus(ApparatusExample example);

    int updateApparatus(Apparatus Apparatus, ApparatusExample example);

    Page<Apparatus> ApparatusList(ApparatusExample example, int pageNum, int pageSize);
}
