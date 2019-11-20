package com.lzq.controller;

import com.lzq.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzq
 */
@RestController
@RequestMapping("Passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExsit")
    public int userNameIsExist(@RequestParam String username){
        if (StringUtils.isBlank(username)){
            return 500;
        }
        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist){
            return 500;
        }
        return 200;
    }
}
