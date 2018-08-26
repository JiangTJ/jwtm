package com.jtj.jwtm.third.base;

import com.jtj.jwtm.repository.ThirdPartyRepository;
import com.jtj.jwtm.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/24.
 *
 * 抽象：第三方服务
 *
 */
@Service
public abstract class AbstractThirdServer {

    private String type;

    @Resource
    private UserRepository userRepository;
    @Resource
    private ThirdPartyRepository thirdPartyRepository;

    public Optional<Long> getUserIdByThirdName(String name){
        return thirdPartyRepository.findUserIdByThirdName(type, name);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
