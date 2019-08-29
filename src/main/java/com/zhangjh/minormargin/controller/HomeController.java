package com.zhangjh.minormargin.controller;

import com.zhangjh.minormargin.annotation.UserLoginToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HomeController
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/29
 * @Version 小缘 1.0
 **/
@Slf4j
@RestController
@RequestMapping("xy")
public class HomeController {

    @UserLoginToken
    @PostMapping("home")
    public String home(@RequestHeader(value = "token") String token){
        log.info("进入主页成功。");
        return token;
    }

    @PostMapping("test")
    public String test(){
        log.info("进入test成功。");
        return "test";
    }

}
