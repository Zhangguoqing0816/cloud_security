package com.sso.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sso.server.entity.UserRole;
import com.sso.server.mapper.UserRoleMapper;
import com.sso.server.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/14 10:51
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
