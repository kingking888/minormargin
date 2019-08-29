package com.zhangjh.minormargin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * jwt工具类 封装一些通用方法
 *
 * @ClassName JwtUtil
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/29
 * @Version 小缘 1.0
 **/
public class JwtUtil {

    /**
     * 获取token
     *
     * @param id       用户id，用于获取用户信息
     * @param password 用户密码加密加盐
     * @return
     */
    public static String getToken(Integer id, String password) {
        return JWT.create().withAudience(String.valueOf(id))
                .sign(Algorithm.HMAC256(password));
    }

}
