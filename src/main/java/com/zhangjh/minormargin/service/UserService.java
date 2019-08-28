package com.zhangjh.minormargin.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zhangjh.minormargin.api.UserApi;
import com.zhangjh.minormargin.constant.ResultCode;
import com.zhangjh.minormargin.entity.User;
import com.zhangjh.minormargin.repository.UserRepository;
import com.zhangjh.minormargin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/28
 * @Version 小缘 1.0
 **/
@Service
public class UserService implements UserApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public Result login(String username, String password) {
        User loginUser = userRepository.findByUsernameAndPassword(username, password);
        if (loginUser == null){
            return new Result(ResultCode.RESULT_FAIL, "登陆失败，用户名不存在或密码错误.");
        }
        Map resultMap = new HashMap<String, String>();
        resultMap.put("token", getToken(loginUser.getId(), loginUser.getPassword()));
        resultMap.put("user", loginUser);
        return new Result(ResultCode.RESULT_SUCCESS, "登陆成功.", resultMap);
    }

    @Override
    public Result register(String username, String password) {

        if (userRepository.existsByUsername(username)){
            return new Result(ResultCode.RESULT_FAIL, "注册失败，用户名已存在.");
        }

        User saveUser = new User();
        saveUser.setUsername(username);
        saveUser.setPassword(password);
        if(userRepository.save(saveUser) == null){
            return new Result(ResultCode.RESULT_FAIL, "注册失败，添加数据库失败.");
        }
        return new Result(ResultCode.RESULT_SUCCESS, "注册成功.");
    }

    /**
     * 获取token
     *
     * @param id
     * @param password
     * @return
     */
    private String getToken(Integer id, String password) {
        return JWT.create().withAudience(String.valueOf(id))
                .sign(Algorithm.HMAC256(password));
    }

}
