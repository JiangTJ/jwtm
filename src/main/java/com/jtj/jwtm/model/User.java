package com.jtj.jwtm.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/23 23:52 End.
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createTime;

    //用户名
    @Column(unique = true, nullable = false, length = 64)
    private String name;

    //密码
    @Column(nullable = false, updatable = false)
    private String password;

    //昵称
    @Column(length = 64)
    private String nick;

    //性别
    @Column
    private Short sex;

    //头像
    @Column
    private String avatar;

    //性别
    @Column
    private String description;

}
