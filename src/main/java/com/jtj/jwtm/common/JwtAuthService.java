package com.jtj.jwtm.common;

import com.jtj.jwtm.dto.PublicUserInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/3/7.
 */
@Slf4j
@Service
public class JwtAuthService {

    @Resource
    private JwtAuthConfig config;

    private SecretKey key;

    public SecretKey getKey(){
        if (key != null) {
            return key;
        }
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(config.getSecret()));
        return key;
    }

    /**
     * 创建token
     */
    public String generateToken(PublicUserInfo user) {

        return Jwts.builder()
                .signWith(getKey())
                .setSubject(user.getName())
                .setIssuer("Jwtm")
                .setIssuedAt(new Date())
                .setAudience("pass")
                .setExpiration(Date.from(Instant.now().plusSeconds(config.getTimeout().getSeconds())))
                .compact();

    }

}
