package com.jtj.jwtm.dto;

import lombok.Data;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 *
 * 注册时返回，基础的用户信息
 *
 */
@Data
public class LoginUserInfo {

    private Long id;
    private String name;

}
