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

    @Column(unique = true, nullable = false, length = 64)
    private String name;

    @Column
    private String nick;

    @Column
    private Short sex;

    @Column(nullable = false, updatable = false)
    private String password;

    @Column
    private String description;

}
