package com.jtj.jwtm.third.base;

import lombok.Data;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
@Data
public class PasswordServer {

    private Type type;//密码登录方式
    private String pattern;//如果MUTLI模式，需要提供匹配正则
    private AbstractThirdServer server;//第三方服务实例


    public enum Type{
        NONE,SINGLE,MUTLI;
    }

}
