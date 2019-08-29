package com.zhangjh.minormargin.vo;

import com.zhangjh.minormargin.constant.ResultCode;
import lombok.Data;

/**
 * 返回数据格式
 *
 * @ClassName Result
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/27
 * @Version 小缘 1.0
 **/
@Data
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public Result(){

    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result success(String msg) {
        return new Result<>(ResultCode.RESULT_SUCCESS, msg);
    }

    public Result success(String msg, T data) {
        return new Result<>(ResultCode.RESULT_SUCCESS, msg, data);
    }

    public Result fail(String msg) {
        return new Result<>(ResultCode.RESULT_FAIL, msg);
    }

    public Result fail(String msg, T data) {
        return new Result<>(ResultCode.RESULT_FAIL, msg, data);
    }

}
