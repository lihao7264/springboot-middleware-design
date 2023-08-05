package com.atlihao.springboot.whitelist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author: lihao726726
 * @CreateDate: 2023/7/11 11:03 上午
 * @UpdateUser: lihao726726
 * @UpdateDate: 2023/7/11 11:03 上午
 * @Version: 1.0.0
 */
@ConfigurationProperties("atlihao.whitelist")
public class WhiteListProperties {

    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
