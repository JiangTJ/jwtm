package com.jtj.jwtm;

import com.jtj.jwtm.third.EmailServer;
import com.jtj.jwtm.third.common.AbstractThirdServer;
import com.jtj.jwtm.third.common.Password;
import com.jtj.jwtm.third.common.PasswordServer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
@Service
public class ThirdServer {

    private List<PasswordServer> multiPasswordServerList = new ArrayList<>();

    @Resource
    private EmailServer emailServer;

    @PostConstruct
    public void initThirdServer(){
        emailServer.setType("email");
        addThirdServer(emailServer);
    }

    private void addThirdServer(AbstractThirdServer thirdServer) {

        String thirdServerType = thirdServer.getType();
        if (StringUtils.isEmpty(thirdServerType)) {
            return;
        }

        try {
            Password password = thirdServer.getClass().getDeclaredAnnotation(Password.class);
            PasswordServer.Type type = password.type();
            String pattern = password.pattern();

            PasswordServer passwordServer = new PasswordServer();
            passwordServer.setType(type);
            passwordServer.setPattern(pattern);
            passwordServer.setServer(thirdServer);

            if (type == PasswordServer.Type.MUTLI || !StringUtils.isEmpty(pattern)) {
                multiPasswordServerList.add(passwordServer);
            }

        }catch(Exception ignored) {
        }

    }

    public PasswordServer getMultiServerByName(String name) {
        for (PasswordServer server: multiPasswordServerList) {
            String pattern = server.getPattern();
            boolean isMatch = Pattern.matches(pattern, name);
            if (isMatch) {
                return server;
            }
        }
        return null;
    }
}
