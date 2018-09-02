package com.jtj.jwtm.common;

import com.jtj.jwtm.dto.PublicUserInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

        String secret = config.getSecret();
        if (StringUtils.isEmpty(secret)) {
            key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            return key;
        }

        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
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
                .setAudience("all")
                .setExpiration(Date.from(Instant.now().plusSeconds(config.getTimeout().getSeconds())))
                .compact();

    }

    /**
     * 创建token
     */
    public String generateCode(String thirdName, String code) {

        Map<String,Object> claims = new HashMap<>();
        claims.put("code", code);

        return Jwts.builder()
                .signWith(getKey())
                .setSubject(thirdName)
                .setIssuer("Jwtm")
                .setIssuedAt(new Date())
                .setAudience("login")
                .setClaims(claims)
                .setExpiration(Date.from(Instant.now().plusSeconds(config.getTemporaryTokenTimeout().getSeconds())))
                .compact();

    }

}
