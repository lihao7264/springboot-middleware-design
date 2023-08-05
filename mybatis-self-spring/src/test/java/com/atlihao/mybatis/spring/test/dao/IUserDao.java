package com.atlihao.mybatis.spring.test.dao;

import com.atlihao.mybatis.spring.test.po.User;

public interface IUserDao {

     User queryUserInfoById(Long id);
}
