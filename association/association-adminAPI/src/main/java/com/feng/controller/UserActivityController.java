package com.feng.controller;


import com.feng.dto.ActivityFileDto;
import com.feng.entity.ResponseResult;

import com.feng.entity.UserActivity;
import com.feng.enums.ErrorEnum;

import com.feng.exception.ParamInvalidException;
import com.feng.service.UserActivityService;
import com.feng.service.RedisOperatorService;
import com.feng.util.ResponseResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/userActivity")
@CrossOrigin(allowCredentials = "true")
@Api(value = "社团管理系统后台用户管理接口",tags = "社团管理系统后台用户管理接口")
public class UserActivityController {
    @Autowired
    private UserActivityService userActivityService;
    @Autowired
    private RedisOperatorService redisOperatorService;

    @PostMapping
    @ApiOperation("添加一个用户")
    public void add(@Valid @RequestBody UserActivity userActivity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            throw new ParamInvalidException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(msg));
        }
        userActivityService.add(userActivity);
//        return ResponseResultUtil.renderSuccess(userActivity.getId());
    }

    @ApiOperation(value = "根据条件分页查询所有活动",notes = "根据条件分页查询所有活动")
    @GetMapping
    public ResponseResult list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize, @RequestParam(value="search", required=false) String search) {

        PageInfo<UserActivity> myActivityList = userActivityService.getPage(pageNum, pageSize, search);
        return ResponseResultUtil.renderSuccess(myActivityList);
    }

//@GetMapping
//public ResponseResult list(@PathVariable(value="pageNum") int pageNum, @PathVariable(value="pageSize") int pageSize, @PathVariable(value="search")  String search) {
//    PageInfo<UserActivity> myActivityList = userActivityService.getPage(pageNum, pageSize, search);
//    return ResponseResultUtil.renderSuccess(myActivityList);
//}





//    @PutMapping
//    @ApiOperation("通过id更新一个用户")
//    public ResponseResult update(@RequestBody @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String msg = bindingResult.getFieldError().getDefaultMessage();
//            throw new ParamInvalidException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(msg));
//        }
//        userService.updateById(user);
//        return ResponseResultUtil.renderSuccess("更新用户成功");
//    }
//
//    @ApiOperation("用户登录接口")
//    @PostMapping("/login")
//    public ResponseResult login(@Valid @RequestBody LoginUserVo userVo, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
//        if (bindingResult.hasErrors()) {
//            String msg = bindingResult.getFieldError().getDefaultMessage();
//            log.error("{}", msg);
//            throw new ParamInvalidException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(msg));
//        }
//        String code = userVo.getCode();
//        String codeKey = CookieUtil.getCookie(request, Constants.KAPTCHA_SESSION_KEY);
//        String katchaCode = (String) redisOperatorService.getValue(codeKey);
//        log.info("{}", katchaCode);
//
//        if (StringUtils.isEmpty(code) || !katchaCode.equals(code.trim())) {
//            throw new BusinessException(ErrorEnum.USER_CODE_ERROR);
//        }
//
//        User loginUser = userService.login(userVo);
//        String token = UUIDUtil.getUUID();
//        //登录成功
//        if (loginUser != null) {
//            userSessionService.saveUserSession(token, loginUser.getId());
//        }
//
//        return ResponseResultUtil.renderSuccess(token);
//    }


//    /**
//     * 注销登录
//     *
//     * @return
//     */
//    @GetMapping("/logout")
//    @ApiOperation("用户退出接口")
//    public ResponseResult logout(HttpServletRequest request) {
//        String token = UserTokenUtils.getUserToken(request);
//        userSessionService.removeUserSession(token);
//        return ResponseResultUtil.renderSuccess("成功退出系统！");
//    }

}

