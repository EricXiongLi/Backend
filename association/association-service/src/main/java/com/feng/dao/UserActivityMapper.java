package com.feng.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.feng.dto.ActivityTypeDto;
import com.feng.entity.Activity;
import com.feng.entity.Passage;
import com.feng.entity.UserActivity;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;


public interface UserActivityMapper extends BaseMapper<UserActivity> {

    int add (UserActivity userActivity);

    List<UserActivity> findActivity(String search);

}
