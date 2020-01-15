package com.sso.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sso.server.entity.Role;
import com.sso.server.mapper.RoleMapper;
import com.sso.server.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/14 10:53
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
