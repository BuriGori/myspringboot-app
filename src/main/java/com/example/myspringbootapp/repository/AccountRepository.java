package com.example.myspringbootapp.repository;

import com.example.myspringbootapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);





    // application에서
    // spring jpa hibernate ddl-auto 는 4가지
    // create 기존 테이블을 삭제하고 새로 만는다
    // create-drop 종료시점에 삭제한다.
    // update 기존 스키마유지하고 새로운 것만 추가한다. 기존의 데이터와 변경된 부분도 반영한다!
    // validate 엔티티와 테이블이 정상 매핑되어있는지 검증한다.
}
