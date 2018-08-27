package com.jtj.jwtm.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/23 23:04 End.
 */
@Configuration
@EnableWebFlux
@EnableJpaAuditing
public class WebConfig implements WebFluxConfigurer {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
