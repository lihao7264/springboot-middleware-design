package com.atlihao.springboot.whitelist.test.interfaces;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: lihao726726
 * @CreateDate: 2023/7/18 11:18 上午
 * @UpdateUser: lihao726726
 * @UpdateDate: 2023/7/18 11:18 上午
 * @Version: 1.0.0
 */
@Data
public class Test implements Serializable {

    /**
     * 获取
     */
    private Integer name;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
