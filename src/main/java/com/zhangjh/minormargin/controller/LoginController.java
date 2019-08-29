package com.zhangjh.minormargin.controller;

import com.zhangjh.minormargin.service.UserService;
import com.zhangjh.minormargin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/28
 * @Version 小缘 1.0
 **/
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("xy_login")
    public Result login(@RequestParam String username,
                        @RequestParam String password){
        return userService.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("xy_register")
    public Result register(@RequestParam String username,
                           @RequestParam String password){
        return userService.register(username, password);
    }
}
