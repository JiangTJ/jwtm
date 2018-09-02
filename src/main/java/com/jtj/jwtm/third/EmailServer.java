package com.jtj.jwtm.third;

import com.jtj.jwtm.common.ErrorResult;
import com.jtj.jwtm.common.JwtAuthService;
import com.jtj.jwtm.common.SecureRandomUtils;
import com.jtj.jwtm.model.ThirdAuthCode;
import com.jtj.jwtm.repository.ThirdAuthCodeRepository;
import com.jtj.jwtm.third.base.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
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
    @Resource
    private ThirdAuthCodeRepository thirdAuthCodeRepository;
    @Resource
    private JwtAuthService jwtAuthService;
    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public Mono<ServerResponse> sendRedirectUri(ServerRequest request) {
        Optional<String> name = request.queryParam("name");
        if (!name.isPresent()) {
            return ServerResponse.badRequest().body(ErrorResult.of(ErrorResult.Code.NO_NEED_PARAMS).toBody());
        }
        String emailName = name.get();
        Optional<String> url = request.queryParam("url");
        if (!url.isPresent()) {
            return ServerResponse.badRequest().body(ErrorResult.of(ErrorResult.Code.NO_NEED_PARAMS).toBody());
        }
        String redirectUri = null;
        try {
            redirectUri = URLDecoder.decode(url.get(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Password password = this.getClass().getDeclaredAnnotation(Password.class);
        if (!Pattern.matches(password.pattern(), emailName)){
            return ServerResponse.badRequest().body(ErrorResult.of(UN_SUPPORT_USER_NAME).toBody());
        }
        String numberCode = SecureRandomUtils.getStringNumber(6);
        String token = jwtAuthService.generateCode(emailName, numberCode);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(fromMail);
            helper.setTo(emailName);
            helper.setSubject("Confirm your mail: " + emailName);
            File mailTemplate = ResourceUtils.getFile("classpath:mail.html");
            String mailContent = StreamUtils.copyToString(new FileInputStream(mailTemplate), StandardCharsets.UTF_8);
            mailContent = mailContent.replace("${email}", emailName);
            mailContent = mailContent.replace("${token-url}", redirectUri + "?token=" + token);
            helper.setText(mailContent, true);
            mailSender.send(message);
            thirdAuthCodeRepository.save(ThirdAuthCode.of(getType(),emailName,numberCode));
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.ok().build();
    }

}
