package com.jtj.jwtm;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/8/23 23:57 End.
 */
@Data
@Entity
public class ThirdParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updateTime;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    private Short type;

    @Column(nullable = false, length = 128)
    private String value;
}
