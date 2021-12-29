package com.feng.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.feng.entity.File;

public interface FileMapper extends BaseMapper<File> {
    Integer save(File file);
}
