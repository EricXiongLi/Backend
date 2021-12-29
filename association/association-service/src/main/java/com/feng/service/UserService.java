package com.feng.service;

import com.baomidou.mybatisplus.service.IService;
import com.feng.entity.Club;
import com.feng.entity.User;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface UserService extends IService<User> {
    PageInfo<User> getUserPage(int num, int size,User search);

    User login(User user);

    List<User> getByAccount(String account);

    User add(User user);

    User updateWithId(User user);

    User getById(Serializable id);

   void  DeleteById(Serializable id);

    User getOneByAccount(String account);

    User register(User user,String rePassWord);
}
