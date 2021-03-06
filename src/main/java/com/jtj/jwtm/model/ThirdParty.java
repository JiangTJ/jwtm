package com.jtj.jwtm.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/23 23:57 End.
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ThirdParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createTime;

    //关联的用户ID
    @Column(nullable = false)
    private Long userId;

    //第三方类型
    @Column(nullable = false, length = 8)
    private String type;

    //第三方编号/名称/OpenId等，能唯一标识第三方的值
    @Column(nullable = false, length = 64)
    private String name;

    //昵称
    @Column(length = 64)
    private String nick;

    //头像
    @Column
    private String avatar;

}
