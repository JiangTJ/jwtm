package com.jtj.jwtm.dto;

import com.jtj.jwtm.model.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 *
 * 注册时返回，基础的用户信息
 *
 */
@Data
public class PublicUserInfo {

    private Long id;
    private String name;

    public static PublicUserInfo fromUser(User user){
        PublicUserInfo info = new PublicUserInfo();
        BeanUtils.copyProperties(user, info);
        return info;
    }

}
