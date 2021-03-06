package com.feng.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.feng.dto.ActivityFileDto;
import com.feng.dto.ActivityTypeDto;
import com.feng.entity.Activity;

import java.util.List;


public interface ActivityMapper extends BaseMapper<Activity> {
    List<ActivityTypeDto> findActivity(Activity activity);

    ActivityFileDto getInfoById(Integer id);

    Integer add(Activity activity);
}
