package com.haohao.demo.springboot.jpa.repository;

import com.haohao.demo.springboot.jpa.entity.DemoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;

@SpringBootTest
class DemoRepositoryTest {

    @Resource
    private DemoRepository demoRepository;

    @Test
    void page() {
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "id"));

        Page<DemoEntity> page = demoRepository.findAll(pageRequest);
        System.out.println("page.getTotalPages()" + page.getTotalPages());
        System.out.println("page.getTotalElements()" + page.getTotalElements());
        page.getContent().forEach(System.out::println);
    }

    @Test
    void findByName() {
        demoRepository.findByName("张三0").forEach(System.out::println);
    }

    @Test
    void findById() {
        System.out.println(demoRepository.findById(10L));
    }
}