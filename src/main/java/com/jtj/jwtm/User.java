package com.jtj.jwtm;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/23 23:52 End.
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updateTime;

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
