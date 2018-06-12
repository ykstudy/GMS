package com.gym.managesystem.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.gym.managesystem.demo.bean.Apparatus;
import com.gym.managesystem.demo.bean.ApparatusExample;
import com.gym.managesystem.demo.service.ApparatusService;
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
@RequestMapping("/App")
@CrossOrigin(maxAge = 3600)//支持跨域
public class ApparatusController {

    @Autowired
    private ApparatusService ApparatusService;
    //日志
    static org.slf4j.Logger logger = LoggerFactory.getLogger(ApparatusController.class);
    @ResponseBody
    @RequestMapping("/addApp")
    public String addApparatus(@RequestBody String jsonData, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String name = jsonObject.getString("name").trim();
        String type = jsonObject.getString("type").trim();
        Apparatus Apparatus = new Apparatus();
        Apparatus.setName(name);
        Apparatus.setType(type);
        int i =  ApparatusService.addApparatus(Apparatus);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/updateApp")
    public String updateApparatus(@RequestBody String jsonData){

        return "success";
    }

    @ResponseBody
    @RequestMapping("/deleteApp")
    public String deleteApparatus(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int id = Integer.parseInt(jsonObject.getString("id"));
        ApparatusExample example = new ApparatusExample();
        ApparatusExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int i  = ApparatusService.deleteApparatus(example);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/AppList")
    public String ApparatusList(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
        Page<Apparatus> page = ApparatusService.ApparatusList(null,pageNo,15);
        JSONObject resultjson = new JSONObject();
        //Page page = PageHelper.getLocalPage();
        resultjson.put("Apparatus",page.getResult());
        resultjson.put("totalRecord",page.getTotal());
        resultjson.put("totalPage",page.getPageNum());
        return resultjson.toJSONString();
    }
}
