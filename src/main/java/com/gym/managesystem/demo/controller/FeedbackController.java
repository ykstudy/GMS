package com.gym.managesystem.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Feedback;
import com.gym.managesystem.demo.bean.FeedbackExample;
import com.gym.managesystem.demo.service.FeedbackService;
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
@RequestMapping("/Feedback")
@CrossOrigin(maxAge = 3600)//支持跨域
public class FeedbackController {

    @Autowired
    private FeedbackService FeedbackService;
    //日志
    static org.slf4j.Logger logger = LoggerFactory.getLogger(FeedbackController.class);
    @ResponseBody
    @RequestMapping("/addFeedback")
    public String addFeedback(@RequestBody String jsonData, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String content = jsonObject.getString("content").trim();
        String title = jsonObject.getString("title").trim();
        String membername = jsonObject.getString("membername").trim();
        Feedback Feedback = new Feedback();
        Feedback.setContent(content);
        Feedback.setMembername(membername);
        Feedback.setTitle(title);
        int i =  FeedbackService.addFeedback(Feedback);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/updateFeedback")
    public String updateFeedback(@RequestBody String jsonData){

        return "success";
    }

    @ResponseBody
    @RequestMapping("/deleteFeedback")
    public String deleteFeedback(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int id = Integer.parseInt(jsonObject.getString("id"));
        FeedbackExample example = new FeedbackExample();
        FeedbackExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int i  = FeedbackService.deleteFeedback(example);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/FeedbackList")
    public String FeedbackList(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
        Page<Feedback> page = FeedbackService.FeedbackList(null,pageNo,15);
        JSONObject resultjson = new JSONObject();
        //Page page = PageHelper.getLocalPage();
        resultjson.put("Feedback",page.getResult());
        resultjson.put("totalRecord",page.getTotal());
        resultjson.put("totalPage",page.getPageNum());
        return resultjson.toJSONString();
    }
}
