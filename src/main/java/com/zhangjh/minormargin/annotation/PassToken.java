package com.zhangjh.minormargin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 免登陆访问接口
 *
 * @ClassName PassToken
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/27
 * @Version 小缘 1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
