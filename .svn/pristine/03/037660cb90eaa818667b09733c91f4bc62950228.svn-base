package com.xzy.mybatis.service.Impl;

import com.xzy.mybatis.dao.UserDao;
import com.xzy.mybatis.model.User;
import com.xzy.mybatis.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XZY on 2017/4/26.
 */
@Service("userService")
public class IUserServiceImpl implements IUserService {
    @Resource
    private UserDao userDao;


    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> selectAll() {
       return userDao.selectAll();
    }

    @Override
    public List<User> selectUserCall() {
        return userDao.selectUserCall();
    }

    @Override
    public List<User> selectUserByID(Integer id) {
        return userDao.selectUserByID(id);
    }


}
