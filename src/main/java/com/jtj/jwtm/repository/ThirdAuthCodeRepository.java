package com.jtj.jwtm.repository;

import com.jtj.jwtm.model.ThirdAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/31.
 */
public interface ThirdAuthCodeRepository extends JpaRepository<ThirdAuthCode,Long> {
}
