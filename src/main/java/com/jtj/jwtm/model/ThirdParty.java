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

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 8)
    private String type;

    @Column(nullable = false, length = 128)
    private String value;
}
