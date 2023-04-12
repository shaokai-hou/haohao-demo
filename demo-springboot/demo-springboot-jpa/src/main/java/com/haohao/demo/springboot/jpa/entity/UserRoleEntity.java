package com.haohao.demo.springboot.jpa.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRoleEntity extends BaseEntity implements Serializable {

    @Column(name = "user_id", columnDefinition = "bigint comment '用户ID'")
    private Long userId;

    @Column(name = "role_id", columnDefinition = "bigint comment '角色ID'")
    private Long roleId;
}
