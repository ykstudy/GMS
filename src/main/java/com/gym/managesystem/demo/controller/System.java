package com.gym.managesystem.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gym.managesystem.demo.bean.Member;
import com.gym.managesystem.demo.bean.MemberExample;
import com.gym.managesystem.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/UserSystem")
@CrossOrigin(maxAge = 3600)//支持跨域
public class System {

    @Autowired
    private MemberService memberService;

    @ResponseBody
    @RequestMapping("/addMember")
    public String addMember(@RequestBody String jsonData, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String name = jsonObject.getString("name").trim();
        String number = jsonObject.getString("number").trim();
        String sex = jsonObject.getString("sex").trim();
        Member member = new Member();
        member.setName(name);
        member.setNumber(number);
        member.setSex(sex);
       int i =  memberService.addMember(member);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/updateMember")
    public String updateMember(@RequestBody String jsonData){

        return "success";
    }

    @ResponseBody
    @RequestMapping("/deleteMember")
    public String deleteMember(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int id = Integer.parseInt(jsonObject.getString("id"));
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int i  = memberService.deleteMember(example);
        if(i > 0){
            return JSONObject.toJSONString("success");
        }else
        {
            return JSONObject.toJSONString("error");
        }

    }

    @ResponseBody
    @RequestMapping("/memberList")
    public String memberList(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        int pageNo = Integer.parseInt(jsonObject.getString("pageNo"));
        Page<Member> page = memberService.memberList(null,pageNo,15);
        JSONObject resultjson = new JSONObject();
        //Page page = PageHelper.getLocalPage();
        resultjson.put("memberUser",page.getResult());
        resultjson.put("totalRecord",page.getTotal());
        resultjson.put("totalPage",page.getPageNum());
        return resultjson.toJSONString();
    }
}
