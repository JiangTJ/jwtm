package com.jtj.jwtm;

import com.jtj.jwtm.base.AbstractJwmtWebTests;
import com.jtj.jwtm.common.JacksonUtils;
import com.jtj.jwtm.dto.PublicUserInfo;
import org.junit.Test;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/26 18:38 End.
 */
public class MainFlowTests extends AbstractJwmtWebTests {

    @Test
    public void testGetPublicUser(){

        //成功
        PublicUserInfo info = new PublicUserInfo();
        info.setId(1L);
        info.setName("admin");
        super.webTestClient.get().uri("/public/user?name={name}","admin")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(JacksonUtils.toJson(info))
                .consumeWith(document("public-user",
                        requestParameters(
                                parameterWithName("name").description("用户名称（或者Multi模式的第三方特征名）")
                        ),
                        responseFields(
                                fieldWithPath("id").description("用户ID"),
                                fieldWithPath("name").description("用户名称")
                        )
                ));

        //失败(可注册的用户名)
        super.webTestClient.get().uri("/public/user?name={name}","nonameindb")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().jsonPath("$.code","20001");

        //失败(不可注册的用户名)
        super.webTestClient.get().uri("/public/user?name={name}","aano@bb.cc")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().jsonPath("$.code","20002");

    }

    @Test
    public void testLoginWithPassword(){

        super.webTestClient.post().uri("/login/password?userId={id}&password={password}","1","123456")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(document("login-password",
                        requestParameters(
                                parameterWithName("userId").description("用户ID"),
                                parameterWithName("password").description("密码")
                        )
                ));
    }

}
