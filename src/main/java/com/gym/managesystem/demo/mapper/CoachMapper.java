package com.gym.managesystem.demo.mapper;

import com.gym.managesystem.demo.bean.Coach;
import com.gym.managesystem.demo.bean.CoachExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoachMapper {
    int countByExample(CoachExample example);

    int deleteByExample(CoachExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coach record);

    int insertSelective(Coach record);

    List<Coach> selectByExample(CoachExample example);

    Coach selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coach record, @Param("example") CoachExample example);

    int updateByExample(@Param("record") Coach record, @Param("example") CoachExample example);

    int updateByPrimaryKeySelective(Coach record);

    int updateByPrimaryKey(Coach record);
}