package com.gym.managesystem.demo.mapper;

import com.gym.managesystem.demo.bean.Apparatus;
import com.gym.managesystem.demo.bean.ApparatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApparatusMapper {
    int countByExample(ApparatusExample example);

    int deleteByExample(ApparatusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Apparatus record);

    int insertSelective(Apparatus record);

    List<Apparatus> selectByExample(ApparatusExample example);

    Apparatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Apparatus record, @Param("example") ApparatusExample example);

    int updateByExample(@Param("record") Apparatus record, @Param("example") ApparatusExample example);

    int updateByPrimaryKeySelective(Apparatus record);

    int updateByPrimaryKey(Apparatus record);
}