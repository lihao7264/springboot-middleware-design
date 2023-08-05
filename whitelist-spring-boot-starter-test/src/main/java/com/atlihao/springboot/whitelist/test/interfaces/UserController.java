package com.atlihao.springboot.whitelist.test.interfaces;

import com.atlihao.springboot.whitelist.annotation.DoWhiteList;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 通过：http://localhost:8081/api/queryUserInfo?userId=aaa
     * 拦截：http://localhost:8081/api/queryUserInfo?userId=123
     */
//    @DoWhiteList(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) {
        logger.info("查询用户信息，userId：{}", userId);
        return new UserInfo("虫虫:" + userId, 19, "天津市东丽区万科赏溪苑14-0000");
    }

    /**
     *
     */
//    @DoWhiteList(key = "address", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
    @PostMapping(path = "/api/queryUserInfos")
    public UserInfo queryUserInfo(@RequestBody UserInfo userInfo) {
        logger.info("查询用户信息，address：{}", userInfo.getAddress());
        return new UserInfo("虫虫:" + userInfo.getAddress(), 19, "天津市东丽区万科赏溪苑14-0000");
    }

    @RequestMapping(path = "/api/queryUserInfos1")
    public UserInfo queryUserInfo1(UserInfo userInfo) {
        logger.info("查询用户信息，address：{}", userInfo.getAddress());
        return new UserInfo("虫虫:" + userInfo.getAddress(), 19, "天津市东丽区万科赏溪苑14-0000");
    }


    public static void main(String[] args) {
        String filedValue = null;
        Object[] tests = new Object[1];
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress("ceshj");
        tests[0] = userInfo;
        String filed = "test.name";
        Test test = new Test();
        test.setName(111111);
        userInfo.setTest(test);
        for (Object arg : tests) {
            try {
                if (!filed.contains(".")) {
                    Field declaredField = arg.getClass().getDeclaredField(filed);
                    declaredField.setAccessible(Boolean.TRUE);
                    Object o = declaredField.get(arg);
                    System.out.println(o);
                    continue;
                }
            } catch (Exception e) {

            }
            try {
                filedValue = BeanUtils.getProperty(arg, filed);
                System.out.println(filedValue);
                continue;
            } catch (Exception e) {

            }
        }
    }

}

