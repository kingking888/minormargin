package com.zhangjh.minormargin.service;

import com.alibaba.fastjson.JSONObject;
import com.zhangjh.minormargin.api.UserApi;
import com.zhangjh.minormargin.entity.User;
import com.zhangjh.minormargin.repository.UserRepository;
import com.zhangjh.minormargin.utils.JwtUtil;
import com.zhangjh.minormargin.utils.VerifyCodeUtil;
import com.zhangjh.minormargin.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/28
 * @Version 小缘 1.0
 **/
@Slf4j
@Service
public class UserService implements UserApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public Result login(String username, String password) {

        User loginUser = userRepository.findByUsernameAndPassword(username, password);
        if (loginUser == null) {
            return new Result().fail("登陆失败，用户名或密码错误.");
        }
        JSONObject data = new JSONObject();
        data.put("token", JwtUtil.getToken(loginUser.getId(), loginUser.getPassword()));
        data.put("user", loginUser);
        log.info("用户" + username + "登陆成功.");
        return new Result().success("登陆成功.", data);
    }

    @Override
    public Result register(String username, String password, String phone, String phoneVerificationCode) {

        //验证手机验证码
        Result isNotResult = VerifyCodeUtil.verificationCode(phoneVerificationCode, phone);
        if (isNotResult != null) {
            return isNotResult;
        }

        //验证用户名是否存在
        if (userRepository.existsByUsername(username)) {
            return new Result().fail("注册失败，用户名已存在.");
        }

        User saveUser = new User();
        saveUser.setUsername(username);
        saveUser.setPassword(password);
        saveUser.setPhone(phone);
        if (userRepository.save(saveUser) == null) {
            log.error("注册失败，用户名 = " + username);
            return new Result().fail("注册失败，添加数据库失败.");
        }
        log.info("用户" + username + "注册成功.");
        return new Result().success("注册成功.");
    }

    @Override
    public Result forgetPassword(String phone, String phoneVerificationCode) {
        if (!userRepository.existsByPhone(phone)) {
            return new Result().fail("手机号不存在.");
        }

        //验证手机验证码
        Result isNotResult = VerifyCodeUtil.verificationCode(phoneVerificationCode, phone);
        if (isNotResult != null) {
            return isNotResult;
        }
        return new Result().success("成功.");
    }

    @Override
    public Result updatePassword(String phone, String newPassword) {
        if (userRepository.updateByPasword(phone, newPassword) == 0) {
            return new Result().fail("修改密码失败.");
        }
        return new Result().success("修改密码成功.");
    }

}
