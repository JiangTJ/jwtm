package com.jtj.jwtm;

import com.jtj.jwtm.third.EmailServer;
import com.jtj.jwtm.third.common.AbstractThirdServer;
import com.jtj.jwtm.third.common.Password;
import com.jtj.jwtm.third.common.PasswordServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/23 23:04 End.
 */
@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {

}
