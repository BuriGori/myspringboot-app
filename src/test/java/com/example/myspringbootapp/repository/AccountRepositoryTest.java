package com.example.myspringbootapp.repository;

import com.example.myspringbootapp.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class AccountRepositoryTest {
    // SpringBootTest를 하게되면 Spring Bean을 사용할 수 있게 해준다.


    @Autowired
    AccountRepository accountRepository;

    @Test
    @Disabled
    public void crud() throws Exception{
        // Insert
        Account account = new Account();
        account.setUsername("user");
        account.setPassword("1234");
        accountRepository.save(account);
        //==========

        Account savedAccount = accountRepository.findByUsername("user");
        System.out.println("ID "+savedAccount.getId());
        System.out.println("username "+savedAccount.getUsername());
    }

    @Test
    public void finder() throws Exception{
        Optional<Account> result = accountRepository.findById(1L);
        Account account =result.get();//없는 값이라면 에러가 발생한다.
        log.info("Account {}",account);

        Account acct = accountRepository.findById(11L).orElse(new Account());
        log.info("new Account hear => {}",acct);

        //orElseGet(Supplier sup)
        //Supplier 의 추상매서드 T get()
        Account acct2 = accountRepository.findById(2L).orElseGet(()-> new Account());
        log.info("acct2 = {}",acct2);

        //orElseThrow( Throeable X )
        Account acct3 = accountRepository.findById(5L).orElseThrow(()->new RuntimeException("Account Not Found"));

    }
}
