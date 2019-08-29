package com.zhangjh.minormargin.controller;

import com.zhangjh.minormargin.service.CodeService;
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

    @Autowired
    CodeService codeService;

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("xy_login")
    public Result login(@RequestParam String username,
                        @RequestParam String password) {
        return userService.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("xy_register")
    public Result register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String phone,
                           @RequestParam String phoneVerificationCode) {
        return userService.register(username, password, phone, phoneVerificationCode);
    }

    /**
     * 忘记密码
     *
     * @param phone                 手机号
     * @param phoneVerificationCode 手机验证码
     * @return
     */
    @PostMapping("xy_forget_password")
    public Result forgetPassword(@RequestParam String phone,
                                 @RequestParam String phoneVerificationCode) {
        return userService.forgetPassword(phone, phoneVerificationCode);
    }

    /**
     * 修改密码
     *
     * @param phone       手机号，用作修改密码条件
     * @param newPassword 新密码
     * @return
     */
    @PostMapping("xy_update_password")
    public Result updatePassword(@RequestParam String phone,
                                 @RequestParam String newPassword) {
        return userService.updatePassword(phone, newPassword);
    }

    /**
     * 获取手机验证码
     *
     * @param phone                    手机号
     * @param graphicsKey              图形验证码对应redis key
     * @param graphicsVerificationCode 图形验证码具体值
     * @return
     */
    @PostMapping("xy_get_phone_verification_code")
    public Result getPhoneVerificationCode(@RequestParam String phone,
                                           @RequestParam String graphicsKey,
                                           @RequestParam String graphicsVerificationCode) {
        return codeService.getPhoneVerificationCode(phone, graphicsKey, graphicsVerificationCode);
    }

    /**
     * 获取图形验证码
     *
     * @return
     */
    @PostMapping("xy_get_graphics_verification_code")
    public Result getGraphicsVerificationCode() {
        return codeService.getGraphicsVerificationCode();
    }

}
