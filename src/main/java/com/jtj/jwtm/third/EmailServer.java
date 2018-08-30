package com.jtj.jwtm.third;

import com.jtj.jwtm.common.ErrorResult;
import com.jtj.jwtm.third.base.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.regex.Pattern;

import static com.jtj.jwtm.common.ErrorResult.Code.UN_SUPPORT_USER_NAME;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 *
 * 邮件服务
 * 邮件作为常用的第三方式登录，一般需要支持作为用户名登录，以及通过邮件发送临时token登录
 *
 */
@Service
@ThirdType("email")
@Password(
        type = PasswordServer.Type.MUTLI,
        pattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
)
public class EmailServer extends AbstractThirdServer implements IDCodeServer {

    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public Mono<ServerResponse> sendCode(String thirdName) {
        Password password = this.getClass().getDeclaredAnnotation(Password.class);
        if (!Pattern.matches(password.pattern(), thirdName)){
            return ServerResponse.badRequest().body(ErrorResult.of(UN_SUPPORT_USER_NAME).toBody());
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(thirdName);
        message.setSubject("验证码");
        message.setText("123456");
        mailSender.send(message);
        return ServerResponse.ok().build();
    }

    @Override
    public Mono<ServerResponse> verifyCode(ServerRequest request) {
        return ServerResponse.ok().build();
    }

}
