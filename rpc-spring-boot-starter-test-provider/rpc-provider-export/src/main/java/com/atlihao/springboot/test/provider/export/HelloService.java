package com.atlihao.springboot.test.provider.export;


import com.atlihao.springboot.test.provider.export.domain.Hi;

public interface HelloService {

    String hi();

    String say(String str);

    String sayHi(Hi hi);

}
