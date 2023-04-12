package com.haohao.demo.springboot.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "demo")
@Data
public class DemoEntity {

    @Id
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}
