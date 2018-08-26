package com.jtj.jwtm.third.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/24 22:41 End.
 *
 * 第三方类型，用于扩展第三方登录方式
 * 请不要添加相同的类型！！
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThirdType {

    String value();

}
