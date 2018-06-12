package com.gym.managesystem.demo.service;

import com.gym.managesystem.demo.bean.User;
import com.gym.managesystem.demo.bean.UserExample;

import java.util.List;

public interface UserService {
    int insertUser(User user);
    int deleteUser(UserExample example);
    int updateUser(User user,UserExample example);
    List<User> seletUser(UserExample example);
}
