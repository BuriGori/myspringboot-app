package com.example.myspringbootapp;

import lombok.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaTest {

    @Test
    public void iterable(){
        List<User> users = Arrays.asList(new User("test1",11),new User("test2",22),new User("test3",33));
        users.forEach(u ->{
            System.out.println(u.toString());
        });
        System.out.println();
        users.forEach(System.out::println);
    }





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
        // 오류를 발생시킨다!
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
@ToString
class User {
    private String name;
    private int age;
}