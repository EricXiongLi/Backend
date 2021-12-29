package com.feng.service;

import com.baomidou.mybatisplus.service.IService;
import com.feng.dto.ActivityTypeDto;
import com.feng.entity.Activity;
import com.feng.entity.Club;
import com.feng.entity.User;
import com.feng.entity.UserActivity;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;


public interface UserActivityService extends IService<UserActivity> {
//    PageInfo<User> getUserPage(int num, int size,User search);

//    User login(User user);

//    List<User> getByAccount(String account);

    UserActivity add(UserActivity userActivity);

    PageInfo<UserActivity> getPage(int pageNum, int pageSize,  String search);


//    User updateWithId(User user);

//    UserActivity getById(Serializable id);
//
//    void  DeleteById(Serializable id);
//
//    User getOneByAccount(String account);
//
//    User register(User user,String rePassWord);
}
