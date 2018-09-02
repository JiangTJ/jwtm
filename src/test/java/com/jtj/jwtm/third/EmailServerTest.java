package com.jtj.jwtm.third;

import com.jtj.jwtm.base.AbstractJwmtWebTests;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/30.
 */
public class EmailServerTest extends AbstractJwmtWebTests {

    @Test
    public void testSendCode() throws UnsupportedEncodingException {
        super.webTestClient.get()
                .uri("/{server}/id/redirect?name={name}&url="+ URLEncoder.encode("http://localhost:9999/email/id/verify","utf-8"),
                        "email",
                        "jiang.taojie@foxmail.com")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("server-id-code",
                        pathParameters(
                                parameterWithName("server").description("第三方服务类型（比如email）")
                        ),
                        requestParameters(
                                parameterWithName("name").description("第三方特征名(比如邮箱、手机号)"),
                                parameterWithName("url").description("跳转地址")
                        )
                ));
    }

}
