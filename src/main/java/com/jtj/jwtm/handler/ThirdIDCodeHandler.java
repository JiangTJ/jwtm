package com.jtj.jwtm.handler;

import com.jtj.jwtm.ThirdServer;
import com.jtj.jwtm.common.ErrorResult;
import com.jtj.jwtm.third.base.IDCodeServer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Optional;

import static com.jtj.jwtm.common.ErrorResult.Code.UN_SUPPORT_THIRD_SERVER;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/29.
 *
 * 解析验证临时token 请求，分发到对应的第三方服务
 *
 */
@Service
public class ThirdIDCodeHandler {

    @Resource
    private ThirdServer thirdServer;

    public Mono<ServerResponse> sendCode(ServerRequest request) {
        Optional<String> name = request.queryParam("name");
        if (!name.isPresent()) {
            return ServerResponse.badRequest().body(ErrorResult.of(ErrorResult.Code.NO_NEED_PARAMS).toBody());
        }
        String serverName = request.pathVariable("server");
        IDCodeServer idCodeServer = thirdServer.getIdCodeServerByName(serverName);
        if (idCodeServer == null) {
            return ServerResponse.badRequest().body(ErrorResult.of(UN_SUPPORT_THIRD_SERVER).toBody());
        }
        return idCodeServer.sendCode(name.get());
    }

    public Mono<ServerResponse> sendRedirectUri(ServerRequest request) {
        String serverName = request.pathVariable("server");
        IDCodeServer idCodeServer = thirdServer.getIdCodeServerByName(serverName);
        if (idCodeServer == null) {
            return ServerResponse.badRequest().body(ErrorResult.of(UN_SUPPORT_THIRD_SERVER).toBody());
        }
        return idCodeServer.sendRedirectUri(request);
    }

    public Mono<ServerResponse> verifyCode(ServerRequest request) {
        String serverName = request.pathVariable("server");
        IDCodeServer idCodeServer = thirdServer.getIdCodeServerByName(serverName);
        if (idCodeServer == null) {
            return ServerResponse.badRequest().body(ErrorResult.of(UN_SUPPORT_THIRD_SERVER).toBody());
        }
        return idCodeServer.verifyCode(request);
    }
}
