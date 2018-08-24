package com.jtj.jwtm;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 */
@Slf4j
public class KeyTest {

    @Test
    public void key() {

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("cwgedvgbfdsvfdvdbdgvfdbsegtrhbytndfvgeavgzsfeswr"));

        String jws = Jwts.builder()
                .signWith(key)
                .setSubject("admin")
                .setIssuer("Jwtm")
                .setIssuedAt(new Date())
                .setAudience("pass")
                .setExpiration(Date.from(Instant.now().plusSeconds(Duration.ofHours(10).getSeconds())))
                .compact();

        log.error(jws);
    }

}
