package com.feng.service;

import com.baomidou.mybatisplus.service.IService;
import com.feng.entity.File;
import com.github.pagehelper.PageInfo;

public interface FileService extends IService<File> {
    Integer save(File file);

    PageInfo<File> getPage(int pageNum, int pageSize, File search);
}
