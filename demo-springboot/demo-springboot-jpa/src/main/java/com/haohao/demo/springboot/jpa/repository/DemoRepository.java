package com.haohao.demo.springboot.jpa.repository;

import com.haohao.demo.springboot.jpa.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {

    List<DemoEntity> findByName(String name);

}
