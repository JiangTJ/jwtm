package com.jtj.jwtm.third.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 *
 * 定义密码登录方式
 * NONE：不支持密码登录
 * SINGLE：仅指定类型密码登录
 * MUTLI：通用登录，该第三方的登录名可作为用户名登录
 *
 * MUTLI下需要配置pattern，匹配方式！！
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    PasswordServer.Type type() default PasswordServer.Type.SINGLE;

    String pattern() default "";

}
