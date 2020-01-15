package com.sso.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sso.server.entity.Resource;
import com.sso.server.mapper.ResourceMapper;
import com.sso.server.service.ResourceService;
import org.springframework.stereotype.Service;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/14 11:00
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
}
