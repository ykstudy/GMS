package com.gym.managesystem.demo.serviceImp;

import com.gym.managesystem.demo.bean.User;
import com.gym.managesystem.demo.bean.UserExample;
import com.gym.managesystem.demo.service.UserService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("UserService")
public class UserServiceImp implements UserService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int insertUser(User user) {
        int index = sqlSessionTemplate.insert("com.gym.managesystem.demo.mapper.UserMapper.insert",user);
        return index;
    }

    @Override
    public int deleteUser(UserExample example) {
        int index = sqlSessionTemplate.delete("com.gym.managesystem.demo.mapper.UserMapper.deleteByExample",example);
        return index;
    }

    @Override
    public int updateUser(User user, UserExample example) {
        int index = sqlSessionTemplate.update("com.gym.managesystem.demo.mapper.UserMapper.updateByExample",example);
        return index;
    }

    @Override
    public List<User> seletUser(UserExample example) {
        List<User> list = sqlSessionTemplate.selectList("com.gym.managesystem.demo.mapper.UserMapper.selectByExample",example);
        return list;
    }
}
