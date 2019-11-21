package com.lzq.controller;

import com.lzq.bo.UserBo;
import com.lzq.pojo.Users;
import com.lzq.service.UserService;
import com.lzq.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzq
 */
@Api(value = "注册登录",tags ={"用于注册登录的接口"})
@RestController
@RequestMapping("Passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes ="用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExsit")
    public JsonResult userNameIsExist(@RequestParam String username){
        if (StringUtils.isBlank(username)){
            return JsonResult.errorMsg("用户名不可以为null");
        }
        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist){
            return JsonResult.errorMsg("用户已经存在");
        }
        return JsonResult.ok();
    }

    @PostMapping("regist")
    @ApiOperation(value = "用户注册",notes ="用户注册",httpMethod = "POST")
    public JsonResult regist(@RequestBody UserBo userBo){
        String password = userBo.getPassword();
        String username = userBo.getUsername();
        String confirmPassword = userBo.getConfirmPassword();
        if (StringUtils.isBlank(username)
                ||StringUtils.isBlank(password)
                || StringUtils.isBlank(confirmPassword)){
            return JsonResult.errorMsg("用户名或密码不可以为空");
        }
        if (StringUtils.length(password) < 6){
            return JsonResult.errorMsg("密码长度不可以小于6");
        }
        if (!password.equals(confirmPassword)){
            return JsonResult.errorMsg("两次输入的密码不一致");
        }
        Users result = userService.createUser(userBo);
        return JsonResult.ok(result);
    }
}
