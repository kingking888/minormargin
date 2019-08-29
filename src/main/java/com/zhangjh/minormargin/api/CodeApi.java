package com.zhangjh.minormargin.api;

import com.zhangjh.minormargin.vo.Result;

/**
 * 验证码Api
 *
 * @ClassName UserApi
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/27
 * @Version 小缘 1.0
 **/
public interface CodeApi {
    /**
     * 获取图形验证码
     *
     * @return
     */
    Result getGraphicsVerificationCode();

    /**
     * 获取手机验证码
     *
     * @param phone
     * @param graphicsKey
     * @param graphicsVerificationCode
     * @return
     */
    Result getPhoneVerificationCode(String phone, String graphicsKey, String graphicsVerificationCode);
}
