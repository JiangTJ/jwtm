package com.jtj.jwtm.third.base;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/28.
 */
public interface IDCodeServer {

    Mono<ServerResponse> sendCode(String thirdName);

    Mono<ServerResponse> verifyCode(ServerRequest request);

}
