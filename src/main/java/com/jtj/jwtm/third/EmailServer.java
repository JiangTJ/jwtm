package com.jtj.jwtm.third;

import com.jtj.jwtm.third.base.AbstractThirdServer;
import com.jtj.jwtm.third.base.Password;
import com.jtj.jwtm.third.base.PasswordServer;
import com.jtj.jwtm.third.base.ThirdType;
import org.springframework.stereotype.Service;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 *
 * 邮件服务
 * 邮件作为常用的第三方式登录，一般需要支持作为用户名登录，以及通过邮件发送临时token登录
 *
 */
@Service
@ThirdType("email")
@Password(
        type = PasswordServer.Type.MUTLI,
        pattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
)
public class EmailServer extends AbstractThirdServer {
}
