package com.haohao.demo.springboot.jpa.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity implements Serializable {

    @Column(name = "role_name", columnDefinition = "varchar(50) comment '角色名称'")
    private String roleName;

}
