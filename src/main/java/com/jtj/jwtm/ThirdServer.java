package com.jtj.jwtm;

import com.jtj.jwtm.third.EmailServer;
import com.jtj.jwtm.third.common.AbstractThirdServer;
import com.jtj.jwtm.third.common.Password;
import com.jtj.jwtm.third.common.PasswordServer;
import com.jtj.jwtm.third.common.ThirdType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
@Slf4j
@Service
public class ThirdServer {

    private List<PasswordServer> multiPasswordServerList = new ArrayList<>();
    private Map<String,PasswordServer> passwordServerMap = new HashMap<>();

    @Resource
    private EmailServer emailServer;

    /**
     * 配置第三方服务
     */
    @PostConstruct
    public void initThirdServer(){
        addThirdServer(emailServer);
    }

    private void addThirdServer(AbstractThirdServer thirdServer) {

        //获取第三方类型
        Class<? extends AbstractThirdServer> tClass = thirdServer.getClass();
        try {
            ThirdType thirdType = tClass.getDeclaredAnnotation(ThirdType.class);
            thirdServer.setType(thirdType.value());
        }catch(Exception ignored) {
            log.warn(tClass.getName() + "未发现注解ThirdType，请添加后重试！");
            return;
        }

        //初始化密码模式
        initPasswordServer(thirdServer);

    }

    private void initPasswordServer(AbstractThirdServer thirdServer) {
        Class<? extends AbstractThirdServer> tClass = thirdServer.getClass();
        try {
            Password password = tClass.getDeclaredAnnotation(Password.class);

            PasswordServer.Type type = password.type();

            //不支持密码模式
            if (type == PasswordServer.Type.NONE) {
                return;
            }

            //添加密码模式服务
            String pattern = password.pattern();
            PasswordServer passwordServer = new PasswordServer();
            passwordServer.setType(type);
            passwordServer.setPattern(pattern);
            passwordServer.setServer(thirdServer);

            passwordServerMap.put(thirdServer.getType(),passwordServer);

            //如果通用方式，则保存在队列，方便通用登录时匹配用户名
            if (type == PasswordServer.Type.MUTLI || !StringUtils.isEmpty(pattern)) {
                multiPasswordServerList.add(passwordServer);
            }

        }catch(Exception ignored) {
            log.info(tClass.getName() + "未发现注解Password，不启用密码模式登录！");
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

    public Map<String, PasswordServer> getPasswordServerMap() {
        return passwordServerMap;
    }

    public PasswordServer getPasswordServerByName(String serverName) {
        return passwordServerMap.getOrDefault(serverName,null);
    }
}
