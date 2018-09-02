package com.jtj.jwtm.third.base;

import com.jtj.jwtm.common.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/28.
 */
public interface IDCodeServer {

    default Mono<ServerResponse> sendCode(String thirdName){
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResult.of(ErrorResult.Code.UN_ACHIEVE_METHOD).toBody());
    }

    default Mono<ServerResponse> sendRedirectUri(ServerRequest request){
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResult.of(ErrorResult.Code.UN_ACHIEVE_METHOD).toBody());
    }

    default Mono<ServerResponse> verifyCode(ServerRequest request){
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResult.of(ErrorResult.Code.UN_ACHIEVE_METHOD).toBody());
    }

}
