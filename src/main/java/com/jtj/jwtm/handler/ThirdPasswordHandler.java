package com.jtj.jwtm.handler;

import com.jtj.jwtm.ThirdServer;
import com.jtj.jwtm.dto.ErrorResult;
import com.jtj.jwtm.dto.LoginUserInfo;
import com.jtj.jwtm.model.User;
import com.jtj.jwtm.repository.UserRepository;
import com.jtj.jwtm.third.base.PasswordServer;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Optional;

import static com.jtj.jwtm.dto.ErrorResult.Code.*;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/26 19:01 End.
 */
@Service
public class ThirdPasswordHandler {

    @Resource
    private UserRepository userRepository;
    @Resource
    private ThirdServer thirdServer;

    /**
     * 获取公开的用户信息 - by 第三方特征名
     */
    public Mono<ServerResponse> getPublicUserWithThird(ServerRequest request) {

        Optional<String> nameOpt = request.queryParam("name");
        if (!nameOpt.isPresent()){
            return ServerResponse.badRequest().body(ErrorResult.of(NO_NEED_PARAMS).toBody());
        }
        String name = nameOpt.get();

        String serverName = request.pathVariable("server");
        PasswordServer passwordServer = thirdServer.getPasswordServerByName(serverName);
        if (passwordServer == null) {
            return ServerResponse.badRequest().body(ErrorResult.of(UN_SUPPORT_THIRD_SERVER).toBody());
        }

        return getLoginUserWithThird(passwordServer, name);
    }

    public Mono<ServerResponse> getLoginUserWithThird(PasswordServer passwordServer, String name) {
        LoginUserInfo info = new LoginUserInfo();
        Optional<Long> userId = passwordServer.getServer().getUserIdByThirdName(name);
        if (!userId.isPresent()) {
            return ServerResponse.status(HttpStatus.NOT_FOUND).body(ErrorResult.of(NO_USER_NOT_REGISTE).toBody());
        }
        User user = userRepository.findById(userId.get()).orElse(new User());
        BeanUtils.copyProperties(user, info);
        return ServerResponse.ok().body(fromObject(info));
    }

}
