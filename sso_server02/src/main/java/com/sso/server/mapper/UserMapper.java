package com.sso.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(String userCode);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}