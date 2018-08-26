package com.jtj.jwtm;

import com.jtj.jwtm.base.AbstractJwmtWebTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@Slf4j
public class JwtmApplicationTests extends AbstractJwmtWebTests {

    @Test
    public void contextLoads() {

        super.webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hi guys! Your server is running!")
                .consumeWith(document("index"));

    }

}
