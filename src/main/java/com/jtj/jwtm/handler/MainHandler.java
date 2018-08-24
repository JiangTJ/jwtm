package com.jtj.jwtm.handler;

import com.jtj.jwtm.ThirdServer;
import com.jtj.jwtm.dto.ErrorResult;
import com.jtj.jwtm.dto.LoginUserInfo;
import com.jtj.jwtm.model.User;
import com.jtj.jwtm.repository.UserRepository;
import com.jtj.jwtm.third.common.PasswordServer;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Optional;

import static com.jtj.jwtm.dto.ErrorResult.Code.NO_NEED_PARAMS;
import static com.jtj.jwtm.dto.ErrorResult.Code.NO_USER_AND_REGISTE;
import static com.jtj.jwtm.dto.ErrorResult.Code.NO_USER_NOT_REGISTE;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
@Service
public class MainHandler {

    @Resource
    private UserRepository userRepository;
    @Resource
    private ThirdServer thirdServer;

    public Mono<ServerResponse> getLoginUser(ServerRequest request) {
        LoginUserInfo info = new LoginUserInfo();

        Optional<String> nameOpt = request.queryParam("name");
        if (!nameOpt.isPresent()){
            return ServerResponse.badRequest().body(ErrorResult.of(NO_NEED_PARAMS).toBody());
        }
        String name = nameOpt.get();

        //第三方匹配
        PasswordServer multiServer = thirdServer.getMultiServerByName(name);
        if (multiServer != null) {
            Optional<Long> userId = multiServer.getServer().getUserIdByThirdName(name);
            if (!userId.isPresent()) {
                return ServerResponse.status(HttpStatus.NOT_FOUND).body(ErrorResult.of(NO_USER_NOT_REGISTE).toBody());
            }
            User user = userRepository.findById(userId.get()).orElse(new User());
            BeanUtils.copyProperties(user, info);
            return ServerResponse.ok().body(fromObject(info));
        }

        //主用户名匹配
        Optional<User> dbUser = userRepository.findByName(name);
        if (!dbUser.isPresent()) {
            return ServerResponse.status(HttpStatus.NOT_FOUND).body(ErrorResult.of(NO_USER_AND_REGISTE).toBody());
        }
        BeanUtils.copyProperties(dbUser.get(), info);
        return ServerResponse.ok().body(fromObject(info));
    }

}
