package com.atlihao.springboot.test;

public class ApiTest {

    public static void main(String[] args) throws InterruptedException {
        ApiTest apiTest = new ApiTest();
        String res01 = apiTest.queryUserInfo(111, 17);
        System.out.println("测试结果：" + res01 + "\r\n");
    }

    public String queryUserInfo(int uId, int age) {
        return "你好，测试 | 精神小伙！";
    }

}
