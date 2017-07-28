package com.xzy.mybatis.service;

import com.xzy.mybatis.model.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XZY on 2017/4/26.
 */
public interface IUserService {
    int insert(User user);
    List<User> selectAll();
    List<User> selectUserCall();
    List<User> selectUserByID(Integer id);
}
