package com.jtj.jwtm.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/8/31.
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ThirdAuthCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createTime;

    //第三方类型
    @Column(nullable = false, length = 8)
    private String type;

    //第三方编号/名称/OpenId等，能唯一标识第三方的值
    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 32)
    private String code;

    public static ThirdAuthCode of(String type, String name, String code){
        ThirdAuthCode thirdAuthCode = new ThirdAuthCode();
        thirdAuthCode.setType(type);
        thirdAuthCode.setName(name);
        thirdAuthCode.setCode(code);
        return thirdAuthCode;
    }

}
