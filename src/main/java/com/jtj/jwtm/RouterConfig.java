package com.jtj.jwtm;

import com.jtj.jwtm.handler.MainHandler;
import com.jtj.jwtm.handler.ThirdIDCodeHandler;
import com.jtj.jwtm.handler.ThirdPasswordHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.Resource;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
@Configuration
public class RouterConfig {

    @Resource
    private MainHandler mainHandler;
    @Resource
    private ThirdPasswordHandler thirdPasswordHandler;
    @Resource
    private ThirdIDCodeHandler thirdIDCodeHandler;

    @Bean
    public RouterFunction<ServerResponse> indexRouter() {
        return route(GET("/"), request -> {
            return ServerResponse.ok().body(fromObject("Hi guys! Your server is running!"));
        });
    }

    @Bean
    public RouterFunction<ServerResponse> mainLoginRouter() {
        return route(GET("/public/user"), mainHandler::getPublicUser)
                .andRoute(POST("/login/password"), mainHandler::loginWithPassword);
    }

    @Bean
    public RouterFunction<ServerResponse> passwordRouter() {
        return nest(path("/{server}"),
                route(GET("/public/user"), thirdPasswordHandler::getPublicUserWithThird)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> idCodeRouter() {
        return nest(path("/{server}"),
                route(GET("/id/code"), thirdIDCodeHandler::sendCode)
                        .andRoute(GET("/id/redirect"), thirdIDCodeHandler::sendRedirectUri)
                        //url地址携带token时，通过get方式
                        .andRoute(GET("/id/verify"), thirdIDCodeHandler::verifyCode)
                        //其他情况下建议post方式
                        .andRoute(POST("/id/verify"), thirdIDCodeHandler::verifyCode)
        );
    }

}
