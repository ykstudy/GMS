package com.gym.managesystem.demo.serviceImp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.gym.managesystem.demo.bean.Course;
import com.gym.managesystem.demo.bean.CourseExample;
import com.gym.managesystem.demo.service.CourseService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CourseService")
public class CourseServiceImp implements CourseService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private PageInterceptor pageInterceptor;
    @Override
    public int addCourse(Course Course) {
        int i = sqlSessionTemplate.insert("com.gym.managesystem.demo.mapper.CourseMapper.insert",Course);
        return i;
    }

    @Override
    public int deleteCourse(CourseExample example) {
        int i  = sqlSessionTemplate.delete("com.gym.managesystem.demo.mapper.CourseMapper.deleteByExample",example);
        return i;
    }

    @Override
    public int updateCourse(Course Course, CourseExample example) {

        return 0;
    }

    @Override
    public Page<Course> CourseList(CourseExample example, int pageNum, int pageSize) {

        Page<Course> page =  PageHelper.startPage(pageNum, pageSize);
         sqlSessionTemplate.selectList("com.gym.managesystem.demo.mapper.CourseMapper.selectByExample",example);
        return page;
    }


}
