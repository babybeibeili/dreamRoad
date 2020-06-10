package com.dream.road.service.impl;

import com.dream.road.entity.User;
import com.dream.road.mapper.UserMapper;
import com.dream.road.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author beibei
 * @since 2020-06-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
