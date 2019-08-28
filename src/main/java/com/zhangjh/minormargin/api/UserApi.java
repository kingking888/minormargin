package com.zhangjh.minormargin.api;

import com.zhangjh.minormargin.vo.Result;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户api
 *
 * @ClassName UserApi
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/27
 * @Version 小缘 1.0
 **/
public interface UserApi {

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    Result login(String username, String password);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    Result register(String username, String password);
}
