package com.gym.managesystem.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Course;
import com.gym.managesystem.demo.bean.CourseExample;
import com.gym.managesystem.demo.service.CourseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/Course")
@CrossOrigin(maxAge = 3600)//支持跨域
public class CourseController {

    @Autowired
    private CourseService CourseService;
    //日志
    static org.slf4j.Logger logger = LoggerFactory.getLogger(CourseController.class);
    @ResponseBody
    @RequestMapping("/addCourse")
    public String addCourse(@RequestBody String jsonData, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String name = jsonObject.getString("name").trim();
        String charge = jsonObject.getString("charge").trim();
        String desc = jsonObject.getString("desc").trim();
        Course course = new Course();
        course.setName(name);
        course.setCharge(charge);
        course.setNote(desc);
        int i =  CourseService.addCourse(course);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/updateCourse")
    public String updateCourse(@RequestBody String jsonData){

        return "success";
    }

    @ResponseBody
    @RequestMapping("/deleteCourse")
    public String deleteCourse(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int id = Integer.parseInt(jsonObject.getString("id"));
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int i  = CourseService.deleteCourse(example);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/CourseList")
    public String CourseList(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
        Page<Course> page = CourseService.CourseList(null,pageNo,15);
        JSONObject resultjson = new JSONObject();
        //Page page = PageHelper.getLocalPage();
        resultjson.put("Course",page.getResult());
        resultjson.put("totalRecord",page.getTotal());
        resultjson.put("totalPage",page.getPageNum());
        return resultjson.toJSONString();
    }
}
