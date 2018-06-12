package com.gym.managesystem.demo.service;

import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Course;
import com.gym.managesystem.demo.bean.CourseExample;

public interface CourseService {

    int addCourse(Course Course);

    int deleteCourse(CourseExample example);

    int updateCourse(Course Course, CourseExample example);

    Page<Course> CourseList(CourseExample example, int pageNum, int pageSize);
}
