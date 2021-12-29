

package com.feng.controller;


import com.feng.dto.PassageFileDto;
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
import com.feng.vo.*;
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
@RequestMapping("/users")
@Api(tags = "社团管理系统前台用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("通过id获取用户详细信息")
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return ResponseResultUtil.renderSuccess(user);
    }

    @ApiOperation("根据条件分页查询所有文章")
    @GetMapping
        public ResponseResult list( @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize, User search) {
        PageInfo<User> userlist = userService.getUserPage(pageNum,pageSize, search);
        return ResponseResultUtil.renderSuccess(userlist);
    }



}




