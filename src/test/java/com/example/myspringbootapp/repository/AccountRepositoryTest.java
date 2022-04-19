package com.example.myspringbootapp.repository;

import com.example.myspringbootapp.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void crud() throws Exception{
        // Insert
        Account account = new Account();
        account.setUsername("user1");
        account.setPassword("1234");
        accountRepository.save(account);
        //==========

        Account savedAccount = accountRepository.findByUsername("user");
        System.out.println("ID "+savedAccount.getId());
        System.out.println("username "+savedAccount.getUsername());
    }
}
