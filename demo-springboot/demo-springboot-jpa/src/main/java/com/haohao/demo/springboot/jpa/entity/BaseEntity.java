package com.haohao.demo.springboot.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint comment '主键ID'")
    private Long id;

    @Column(name = "deleted", columnDefinition = "int comment '逻辑删除 0:未删除 1:已删除'")
    private Integer deleted;

    @Column(name = "version", columnDefinition = "int comment '乐观锁'")
    private Integer version;

    @Column(name = "creator", columnDefinition = "bigint comment '创建者'")
    private Long creator;

    @Column(name = "create_time", columnDefinition = "datetime comment '创建时间'")
    private LocalDateTime createTime;

    @Column(name = "updater", columnDefinition = "bigint comment '更新者'")
    private Long updater;

    @Column(name = "update_time", columnDefinition = "datetime comment '更新时间'")
    private LocalDateTime updateTime;
}
