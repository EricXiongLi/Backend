package com.feng.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feng.dao.UserActivityMapper;
import com.feng.dto.ActivityTypeDto;
import com.feng.entity.Activity;
import com.feng.entity.User;
import com.feng.entity.UserActivity;
import com.feng.service.UserActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Service
public class UserActivityServiceImpl extends ServiceImpl<UserActivityMapper, UserActivity> implements UserActivityService {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserActivityMapper userActivityMapper;

//    @Override
//    public PageInfo<User> getUserPage(int num, int size, User search) {
//        PageHelper.startPage(num, size);
//        Wrapper<User> userWrapper = new EntityWrapper();
//        List<User> userList = userMapper.selectList(userWrapper);
//        return new PageInfo<>(userList);
//    }
//
//    @Override
//    public User login(User user) {
//        List<User> userList = getByAccount(user.getAccount());
//        if (userList.isEmpty()) {
//            throw new BusinessException(ErrorEnum.USER_NAME_ERROR);
//        }
//        User loginUser = userList.get(0);
//        if (!loginUser.getPassword().equals(user.getPassword())) {
//            throw new BusinessException(ErrorEnum.USER_PASSWORD_ERROR);
//        }
//        return loginUser;
//    }
//
//    @Override
//    public List<User> getByAccount(String account) {
//        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
//        userEntityWrapper.eq("account", account);
//        return userMapper.selectList(userEntityWrapper);
//    }
//
//    @Override
//    public User getOneByAccount(String account) {
//        List<User> userList = getByAccount(account);
//        if (CollectionUtils.isEmpty(userList)) {
//            return null;
//        } else {
//            return   userList.get(0);
//        }
//    }

    @Override
    public UserActivity add(UserActivity userActivity) {
        userActivityMapper.insert(userActivity);
        return userActivity;
    }

    @Override
    public PageInfo<UserActivity> getPage(int pageNum, int pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserActivity> activityList = userActivityMapper.findActivity(search);
        return new PageInfo<>(activityList);
    }

//    @Override
//    @CachePut(value = "user", key = "#user.id")
//    public User updateWithId(User user) {
//        userMapper.updateById(user);
//        return user;
//    }

//    @Override
//    @Cacheable(value = "user", key = "#id")
//    public User getById(Serializable id) {
//        User user = userMapper.selectById(id);
//        if (user == null) {
//            throw new BusinessException(ErrorEnum.BUSINESS_EXCEPTION.setMsg("该用户不存在"));
//        }
//        return user;
//    }

//    @Override
//    @CacheEvict(value = "user", key = "#id")
//    public void DeleteById(Serializable id) {
//        userMapper.deleteById(id);
//    }

//    @Override
//    public User register(User user, String rePassword) {
////        Assert.notNull(rePassword,"确认密码不能为空！");
//        List<User> userList = getByAccount(user.getAccount());
//        if (!userList.isEmpty()) {
//            User user1 = userList.get(0);
//            if (user1.getActive()) {
//                throw new BusinessException(ErrorEnum.BUSINESS_EXCEPTION.setMsg("邮箱已经激活，请直接登录"));
//            } else {
//                throw new BusinessException(ErrorEnum.BUSINESS_EXCEPTION.setMsg("请尽快激活邮箱"));
//            }
//        }
//        if (user.getPassword().equals(rePassword)) {
//            throw new BusinessException(ErrorEnum.USER_RE_PASSWORD_ERROR);
//        }
//        userMapper.insert(user);
//
//        return user;
//    }
}
