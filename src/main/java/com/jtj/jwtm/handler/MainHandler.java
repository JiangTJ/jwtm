package com.jtj.jwtm.handler;

import com.jtj.jwtm.ThirdServer;
import com.jtj.jwtm.dto.ErrorResult;
import com.jtj.jwtm.dto.LoginUserInfo;
import com.jtj.jwtm.model.User;
import com.jtj.jwtm.repository.UserRepository;
import com.jtj.jwtm.third.base.PasswordServer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static com.jtj.jwtm.dto.ErrorResult.Code.NO_NEED_PARAMS;
import static com.jtj.jwtm.dto.ErrorResult.Code.NO_USER_AND_REGISTE;
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
    @Resource
    private ThirdPasswordHandler thirdHandler;

    /**
     * 获取公开的用户信息
     */
    public Mono<ServerResponse> getPublicUser(ServerRequest request) {
        LoginUserInfo info = new LoginUserInfo();

        Optional<String> nameOpt = request.queryParam("name");
        if (!nameOpt.isPresent()){
            return ServerResponse.badRequest().body(ErrorResult.of(NO_NEED_PARAMS).toBody());
        }
        String name = nameOpt.get();

        //第三方匹配
        PasswordServer multiServer = thirdServer.getMultiServerByName(name);
        if (multiServer != null) {
            return thirdHandler.getLoginUserWithThird(multiServer, name);
        }

        //主用户名匹配
        Optional<User> dbUser = userRepository.findByName(name);
        if (!dbUser.isPresent()) {
            return ServerResponse.status(HttpStatus.NOT_FOUND).body(ErrorResult.of(NO_USER_AND_REGISTE).toBody());
        }
        BeanUtils.copyProperties(dbUser.get(), info);
        return ServerResponse.ok().body(fromObject(info));
    }

    /**
     * 密码模式：登陆
     */
    public Mono<ServerResponse> loginWithPassword(ServerRequest request) {
        Optional<String> userId = request.queryParam("userId");
        String password = request.queryParam("password").orElse("");
        if (!userId.isPresent()){
            return ServerResponse.badRequest().body(ErrorResult.of(NO_NEED_PARAMS).toBody());
        }
        long id = Long.parseLong(userId.get());
        User user = userRepository.findById(id).orElse(new User());
        //todo 加密
        if (!password.equals(user.getPassword())) {
            return ServerResponse.notFound().build();
        }

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("cwgedvgbfdsvfdvdbdgvfdbsegtrhbytndfvgeavgzsfeswr"));

        String jws = Jwts.builder()
                .signWith(key)
                .setSubject(user.getName())
                .setIssuer("Jwtm")
                .setIssuedAt(new Date())
                .setAudience("pass")
                .setExpiration(Date.from(Instant.now().plusSeconds(Duration.ofHours(10).getSeconds())))
                .compact();

        return ServerResponse.ok().body(fromObject(jws));
    }

}
