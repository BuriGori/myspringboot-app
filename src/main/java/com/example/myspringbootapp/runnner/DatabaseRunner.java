package com.example.myspringbootapp.runnner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
public class DatabaseRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("데이터 소스 구현 클래스 "+ dataSource.getClass().getName());
        //ctrl + alt + v ==> return type 자동 생성
        Connection connection = dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("DB URL => " + metaData.getURL());
        System.out.println("DB Vender => " + metaData.getDatabaseProductName());
        System.out.println("DB username => " + metaData.getUserName());
    }
}
