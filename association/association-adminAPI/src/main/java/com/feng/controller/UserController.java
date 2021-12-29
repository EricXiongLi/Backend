package com.feng.controller;
import com.feng.entity.ResponseResult;
import com.feng.entity.User;
import com.feng.enums.ErrorEnum;
import com.feng.exception.BusinessException;
import com.feng.exception.ParamInvalidException;
import com.feng.service.UserService;
import com.feng.service.UserSessionService;
import com.feng.util.CookieUtil;
import com.feng.service.RedisOperatorService;
import com.feng.util.ResponseResultUtil;
import com.feng.util.UUIDUtil;
import com.feng.util.UserTokenUtils;
import com.feng.vo.LoginUserVo;
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
@RequestMapping("/users")
@Slf4j
@CrossOrigin(allowCredentials = "true")
@Api(value = "社团管理系统后台用户管理接口",tags = "社团管理系统后台用户管理接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisOperatorService redisOperatorService;
    @Autowired
    private UserSessionService userSessionService;

    @GetMapping("/{id}")
    @ApiOperation("通过id获取一个用户")
    public ResponseResult getById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return ResponseResultUtil.renderSuccess(user);
    }

    @GetMapping("/getUser")
    @ApiOperation("通过token获取一个用户")
    public ResponseResult getByToken(@RequestParam(defaultValue = "") String token) {
        Integer userId = userSessionService.getUserId(token);
        User user = userService.getById(userId);
        return ResponseResultUtil.renderSuccess(user);
    }

    @GetMapping
    @ApiOperation("根据条件分页查询所有用户")
    public ResponseResult list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize, User search) {
        PageInfo<User> userList = userService.getUserPage(pageNum, pageSize, search);
        return ResponseResultUtil.renderSuccess(userList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过id删除一个用户")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        userService.DeleteById(id);
        return ResponseResultUtil.renderSuccess(id);
    }

    @PostMapping
    @ApiOperation("添加一个用户")
    public ResponseResult add(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            throw new ParamInvalidException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(msg));
        }
        userService.add(user);
        System.out.println(user.getId());
        return ResponseResultUtil.renderSuccess(user.getId());
    }

    @PutMapping
    @ApiOperation("通过id更新一个用户")
    public ResponseResult update(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            throw new ParamInvalidException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(msg));
        }
        userService.updateById(user);
        return ResponseResultUtil.renderSuccess("更新用户成功");
    }

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public ResponseResult login(@Valid @RequestBody LoginUserVo userVo, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            log.error("{}", msg);
            throw new ParamInvalidException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(msg));
        }
        String code = userVo.getCode();//拿到了前端传过来的code，准备验证
        String codeKey = CookieUtil.getCookie(request, Constants.KAPTCHA_SESSION_KEY);//codekey，其实就是KaptchaController在cookie中写的“captchakey”
        String katchaCode = (String) redisOperatorService.getValue(codeKey);//读取由kaptchaController之前写进去的，与“captchakey”这个key对应的value，也就是正确的createtext
        log.info("{}", katchaCode);
        if (StringUtils.isEmpty(code) || !katchaCode.equals(code.trim())) {
            throw new BusinessException(ErrorEnum.USER_CODE_ERROR);
        }

        User loginUser = userService.login(userVo);
        String token = UUIDUtil.getUUID();
        //登录成功
        if (loginUser != null) {
            userSessionService.saveUserSession(token, loginUser.getId());
        }

        return ResponseResultUtil.renderSuccess(token);
    }


    /**
     * 注销登录
     *
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation("用户退出接口")
    public ResponseResult logout(HttpServletRequest request) {
       String token = UserTokenUtils.getUserToken(request);
        userSessionService.removeUserSession(token);
        return ResponseResultUtil.renderSuccess("成功退出系统！");
    }

}

