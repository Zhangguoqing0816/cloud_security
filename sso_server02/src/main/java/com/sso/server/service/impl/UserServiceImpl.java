package com.sso.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sso.server.entity.User;
import com.sso.server.mapper.UserMapper;
import com.sso.server.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/14 10:03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
