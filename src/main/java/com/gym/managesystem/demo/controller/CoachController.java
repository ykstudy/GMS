package com.gym.managesystem.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Coach;
import com.gym.managesystem.demo.bean.CoachExample;
import com.gym.managesystem.demo.service.CoachService;
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
@RequestMapping("/Coach")
@CrossOrigin(maxAge = 3600)//支持跨域
public class CoachController {

    @Autowired
    private CoachService CoachService;
    //日志
    static org.slf4j.Logger logger = LoggerFactory.getLogger(CoachController.class);
    @ResponseBody
    @RequestMapping("/addCoach")
    public String addCoach(@RequestBody String jsonData, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String name = jsonObject.getString("name").trim();
        String age = jsonObject.getString("age").trim();
        String sex = jsonObject.getString("sex").trim();
        Coach coach = new Coach();
        coach.setName(name);
        coach.setAge(Integer.parseInt(age));
        coach.setSex(sex);
        int i =  CoachService.addCoach(coach);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/updateCoach")
    public String updateCoach(@RequestBody String jsonData){

        return "success";
    }

    @ResponseBody
    @RequestMapping("/deleteCoach")
    public String deleteCoach(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int id = Integer.parseInt(jsonObject.getString("id"));
        CoachExample example = new CoachExample();
        CoachExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int i  = CoachService.deleteCoach(example);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/CoachList")
    public String CoachList(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
        Page<Coach> page = CoachService.CoachList(null,pageNo,15);
        JSONObject resultjson = new JSONObject();
        //Page page = PageHelper.getLocalPage();
        resultjson.put("Coach",page.getResult());
        resultjson.put("totalRecord",page.getTotal());
        resultjson.put("totalPage",page.getPageNum());
        return resultjson.toJSONString();
    }
}
