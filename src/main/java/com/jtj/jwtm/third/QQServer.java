package com.jtj.jwtm.third;

import com.jtj.jwtm.third.base.Password;
import com.jtj.jwtm.third.base.PasswordServer;
import com.jtj.jwtm.third.base.ThirdType;
import org.springframework.stereotype.Service;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/28.
 */
@Service
@ThirdType("qq")
@Password(type = PasswordServer.Type.NONE)
public class QQServer {
}
