package com.zhangjh.minormargin.vo;

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

    public Result() {

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
}
