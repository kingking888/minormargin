package com.zhangjh.minormargin.controller;

import com.zhangjh.minormargin.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HomeController
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/27
 * @Version 小缘 1.0
 **/
@Slf4j
@RestController
public class HomeController {

    @GetMapping("test")
    public String test(){
        RedisUtil.set("test", "test");
        System.out.println(RedisUtil.get("test"));
        return "ok";
    }

}
