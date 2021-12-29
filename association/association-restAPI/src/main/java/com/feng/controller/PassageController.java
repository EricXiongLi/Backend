package com.feng.controller;


import com.feng.dto.PassageFileDto;
import com.feng.dto.PassageTypeDto;
import com.feng.entity.ResponseResult;
import com.feng.entity.User;
import com.feng.enums.ErrorEnum;
import com.feng.exception.BusinessException;
import com.feng.exception.ParamInvalidException;
import com.feng.service.RedisOperatorService;
import com.feng.service.UserService;
import com.feng.util.CookieUtil;
import com.feng.util.ResponseResultUtil;
import com.feng.entity.Passage;
import com.feng.service.PassageService;
import com.feng.util.UUIDUtil;
import com.feng.vo.PassagePageVo;
import com.feng.vo.PassageVo;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;






@RestController
@CrossOrigin
@RequestMapping("/passages")
@Api(tags = "社团管理系统前台文章接口")
public class PassageController {
    @Autowired
    private PassageService passageService;
    @ApiOperation("通过id获取一篇文章详细信息")
    @GetMapping("/{id}")
    public ResponseResult getInfoById(@PathVariable("id") Integer id) {
        PassageFileDto passageInfoVo = passageService.getInfoById(id);
        return ResponseResultUtil.renderSuccess(passageInfoVo);
    }


    @ApiOperation("根据条件分页查询所有文章")
    @GetMapping
    public ResponseResult list(Integer passageTypeId, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize) {
        PassagePageVo passagePageVo = passageService.getPageWithTypeList(pageNum, pageSize,passageTypeId);
        return ResponseResultUtil.renderSuccess(passagePageVo);
    }


//    @GetMapping
//    @ApiOperation("根据条件分页查询所有文章")
//    public ResponseResult list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize, Passage search) {
//        PageInfo<PassageTypeDto> passageList = passageService.findPage(pageNum, pageSize, search);
//        return ResponseResultUtil.renderSuccess(passageList);
//    }



    @ApiOperation("查找最近发布的n篇新闻")
    @GetMapping("/top/{n}")
    public ResponseResult getTopN(Passage search, @PathVariable("n") int n) {
        PassageVo passageVo = passageService.getTopN(n,search);
        return ResponseResultUtil.renderSuccess(passageVo);
    }



}


