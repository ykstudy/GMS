package com.gym.managesystem.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gym.managesystem.demo.bean.User;
import com.gym.managesystem.demo.bean.UserExample;
import com.gym.managesystem.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
@CrossOrigin(maxAge = 3600)//支持跨域
public class Login {

    @Autowired
    UserService userService;
    //日志
    static Logger logger = LoggerFactory.getLogger(Login.class);

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody String userInfo, HttpSession session){
        Object obj = session.getAttribute("user");
        if(obj != null)
        {
            return "login";
        }
        JSONObject jsonObject  = JSON.parseObject(userInfo);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria= userExample.createCriteria();
        userCriteria.andUsernameEqualTo(username);
        userCriteria.andPasswordEqualTo(password);
        List<User> user = userService.seletUser(userExample);
        logger.info(String.valueOf(user.size()));
        if(user != null && user.size() > 0)
        {
            session.setAttribute("user",user.get(0));
            return "success";}
        else
            return "error";
    }

    @RequestMapping("/loginout")
    public ModelAndView loginout(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Object obj = session.getAttribute("user");
        if(obj != null)
        {
            session.removeAttribute("user");
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody String registerInfo,HttpSession session){
        JSONObject jsonObject  = JSON.parseObject(registerInfo);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        int index = userService.insertUser(user);
        if(index > 0)
        {
            session.setAttribute("user",user);
            return "success";
        }else
        {
            return "error";
        }
    }
}

