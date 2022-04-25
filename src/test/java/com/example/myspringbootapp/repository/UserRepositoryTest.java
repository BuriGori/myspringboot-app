package com.example.myspringbootapp.repository;

import com.example.myspringbootapp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Disabled
    public void userTest(){

        User user1 = new User();
        user1.setName("test1");
        user1.setEmail("test1@test.com");
        userRepository.save(user1);

        Optional<User> guest = userRepository.findByName("test1");
        log.info("Guest = {}",guest);

    }





}
