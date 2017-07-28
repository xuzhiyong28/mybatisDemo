package com.xzy.mybatis.service.Impl;

import com.xzy.mybatis.model.User;
import com.xzy.mybatis.util.redis.AbstractBaseRedisDao;
import org.springframework.stereotype.Service;

/**
 * Created by XZY on 2017/5/1.
 */
@Service("redisServiceImpl")
public class RedisServiceImpl extends AbstractBaseRedisDao<String, User> {

}
