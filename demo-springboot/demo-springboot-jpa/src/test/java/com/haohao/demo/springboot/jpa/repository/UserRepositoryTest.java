package com.haohao.demo.springboot.jpa.repository;

import com.haohao.demo.springboot.jpa.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;


//    @Test
    void save() {
        userRepository.save(UserEntity.builder().username("admin").password("admin123").createTime(LocalDateTime.now()).build());
    }

//    @Test
    void page() {
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "id"));

        Page<UserEntity> page = userRepository.findAll(pageRequest);
        System.out.println("page.getTotalPages()" + page.getTotalPages());
        System.out.println("page.getTotalElements()" + page.getTotalElements());
        page.getContent().forEach(System.out::println);
    }

    @Test
    @Transactional
    void findById() {
        UserEntity userEntity = userRepository.findById(1L).orElse(null);
        System.out.println(userEntity);
    }
}