package com.jtj.jwtm.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/3/9.
 */
@Data
@Component
@ConfigurationProperties("jwt")
public class JwtAuthConfig {

    private String header = "Authorization";
    private String prefix = "Bearer ";
    private String secret = "";//密钥
    private Duration timeout = Duration.ofHours(10L);//配置token有效期
    private Duration maxTimeout = Duration.ofDays(30);//最大到期时间，用于限制最长token有效期

}
