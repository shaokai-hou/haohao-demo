package com.haohao.demo.springboot.jpa.repository;

import com.haohao.demo.springboot.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
