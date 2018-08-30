package com.jtj.jwtm;

import com.jtj.jwtm.base.AbstractJwmtWebTests;
import com.jtj.jwtm.common.JacksonUtils;
import com.jtj.jwtm.dto.PublicUserInfo;
import org.junit.Test;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/26 21:31 End.
 */
public class ThirdPasswordFlowTests extends AbstractJwmtWebTests {

    @Test
    public void testGetThirdPublicUser(){

        PublicUserInfo info = new PublicUserInfo();
        info.setId(1L);
        info.setName("admin");
        info.setNick("管理员");
        super.webTestClient.get().uri("/{server}/public/user?name={name}","email","aa@bb.cc")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(JacksonUtils.toJson(info))
                .consumeWith(document("server-public-user",
                        pathParameters(
                                parameterWithName("server").description("第三方服务类型（比如email）")
                        ),
                        requestParameters(
                                parameterWithName("name").description("第三方特征名")
                        ),
                        responseFields(
                                fieldWithPath("id").description("用户ID"),
                                fieldWithPath("name").description("用户名称"),
                                fieldWithPath("nick").description("昵称"),
                                fieldWithPath("description").description("简介")
                        )
                ));
    }

}
