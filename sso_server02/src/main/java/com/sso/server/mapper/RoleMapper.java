package com.sso.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sso.server.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    int deleteByPrimaryKey(String roleCode);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleCode);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}