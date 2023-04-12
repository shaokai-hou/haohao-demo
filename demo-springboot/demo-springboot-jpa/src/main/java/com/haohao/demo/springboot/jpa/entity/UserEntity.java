package com.haohao.demo.springboot.jpa.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(name = "username", columnDefinition = "varchar(100) comment '用户名'")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(100) comment '密码'")
    private String password;

    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @ManyToMany(targetEntity = RoleEntity.class)
    private Set<RoleEntity> roles;
}
