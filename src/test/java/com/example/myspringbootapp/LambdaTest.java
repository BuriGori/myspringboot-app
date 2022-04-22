package com.example.myspringbootapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LambdaTest {

    @Test @Disabled
    public void runnable() throws Exception{
        // 1. Anonymous Inner 클래스
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Inner");
            }
        });
        t1.start();

        // 2. MyRunnable 클래스 사용
        Thread t2 = new Thread(new Myrunnable());
        t2.start();


        // 3. 간소화 시키는 작업
        // Thread 생성시 필요한 argument가 Runable이므로 항상 Runable를 구현한 객체가 들어와야하므로
        // 구현해야될 함수는 run()밖에 없다.
        Thread t3 = new Thread(() -> System.out.println("simple lambda??"));
        t3.start();
    }

}

class Myrunnable implements Runnable{
    @Override
    public void run(){
        System.out.println("myRunnalbe test");
    }
}

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private int age;
}