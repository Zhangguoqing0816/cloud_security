package com.sso.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.server.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {
    int deleteByPrimaryKey(String resourceCode);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(String resourceCode);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}