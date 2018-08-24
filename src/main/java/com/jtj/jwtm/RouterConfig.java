package com.jtj.jwtm;

import com.jtj.jwtm.handler.MainHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.Resource;

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

    @Bean
    public RouterFunction<ServerResponse> mainLoginRouter() {
        return route(GET("/login/user"), mainHandler::getLoginUser)
                .andRoute(POST("/login/password"), mainHandler::loginWithPassword);
    }

    @Bean
    public RouterFunction<ServerResponse> passwordRouter() {
        return nest(path("/{server}"),
                route(GET("/login/user"), mainHandler::getLoginUserWithThird)
        );
    }

}
