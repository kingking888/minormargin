package com.zhangjh.minormargin.service;

import com.alibaba.fastjson.JSONObject;
import com.zhangjh.minormargin.api.CodeApi;
import com.zhangjh.minormargin.utils.RedisUtil;
import com.zhangjh.minormargin.utils.VerifyCodeUtil;
import com.zhangjh.minormargin.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName CodeService
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/29
 * @Version 小缘 1.0
 **/
@Service
public class CodeService implements CodeApi {

    @Value("${base64.img.type}")
    private String imgType;

    @Override
    public Result getGraphicsVerificationCode() {
        JSONObject data = new JSONObject();
        VerifyCodeUtil verifyCode = new VerifyCodeUtil();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String image = verifyCode.drawImage();
        String code = verifyCode.getCode();
        data.put("img", imgType + image);
        data.put("graphicsKey", uuid);
        data.put("graphicsVerificationCode", code);
        RedisUtil.set(uuid, code, 60 * 5);
        return new Result().success("获取图形验证码成功.", data);
    }

    @Override
    public Result getPhoneVerificationCode(String phone, String graphicsKey, String graphicsVerificationCode) {

        //验证图形验证码
        Result isNotResult = VerifyCodeUtil.verificationCode(graphicsVerificationCode, graphicsKey);
        if (isNotResult != null) {
            return isNotResult;
        }

        RedisUtil.set(phone, "123456", 60 * 5);
        return new Result().success("获取手机验证码成功，测试版全部为123456");
    }

}
