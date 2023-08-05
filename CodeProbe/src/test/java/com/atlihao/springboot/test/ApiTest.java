package com.atlihao.springboot.test;

import java.util.Random;

/**
 *
 * -javaagent:/Users/admin/Desktop/project/company/km-bot/2023-07-04/springboot-middleware-design/CodeProbe/target/CodeProbe.jar=com.atlihao.springboot.test
 */
public class ApiTest {

    public String queryUserInfo(String uid, String token) throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
        return "你好！";
    }

    public static void main(String[] args) throws InterruptedException {
        String res = new ApiTest().queryUserInfo("100001", "LikdlNL13423");
        System.out.println(res);
    }

}
