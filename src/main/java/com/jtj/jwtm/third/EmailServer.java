package com.jtj.jwtm.third;

import com.jtj.jwtm.third.common.AbstractThirdServer;
import com.jtj.jwtm.third.common.Password;
import com.jtj.jwtm.third.common.PasswordServer;
import org.springframework.stereotype.Service;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
@Service
@Password(type = PasswordServer.Type.MUTLI, pattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
public class EmailServer extends AbstractThirdServer {
}
