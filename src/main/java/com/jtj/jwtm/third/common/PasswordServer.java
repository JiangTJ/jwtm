package com.jtj.jwtm.third.common;

import lombok.Data;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
@Data
public class PasswordServer {

    private Type type;
    private String pattern;//如果MUTLI模式，需要提供匹配正则
    private AbstractThirdServer server;


    public enum Type{
        NONE,SINGLE,MUTLI;
    }

}
