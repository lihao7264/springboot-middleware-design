package com.atlihao.springboot.test.provider.interfaces;

import com.atlihao.springboot.test.provider.export.HelloService;
import com.atlihao.springboot.test.provider.export.domain.Hi;
import org.springframework.stereotype.Controller;

@Controller("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hi() {
        return "hi test rpc";
    }

    @Override
    public String say(String str) {
        return str;
    }

    @Override
    public String sayHi(Hi hi) {
        return hi.getUserName() + " say：" + hi.getSayMsg();
    }

}
