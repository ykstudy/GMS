package com.gym.managesystem.demo.serviceImp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.gym.managesystem.demo.bean.Apparatus;
import com.gym.managesystem.demo.bean.ApparatusExample;
import com.gym.managesystem.demo.service.ApparatusService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ApparatusService")
public class ApparatusServiceImp implements ApparatusService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private PageInterceptor pageInterceptor;
    @Override
    public int addApparatus(Apparatus Apparatus) {
        int i = sqlSessionTemplate.insert("com.gym.managesystem.demo.mapper.ApparatusMapper.insert",Apparatus);
        return i;
    }

    @Override
    public int deleteApparatus(ApparatusExample example) {
        int i  = sqlSessionTemplate.delete("com.gym.managesystem.demo.mapper.ApparatusMapper.deleteByExample",example);
        return i;
    }

    @Override
    public int updateApparatus(Apparatus Apparatus, ApparatusExample example) {

        return 0;
    }

    @Override
    public Page<Apparatus> ApparatusList(ApparatusExample example, int pageNum, int pageSize) {

        Page<Apparatus> page =  PageHelper.startPage(pageNum, pageSize);
         sqlSessionTemplate.selectList("com.gym.managesystem.demo.mapper.ApparatusMapper.selectByExample",example);
        return page;
    }


}
