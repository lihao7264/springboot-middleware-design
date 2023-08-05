package com.atlihao.springboot.test.infrastructure.dao;


import com.atlihao.springboot.test.infrastructure.po.User;

public interface IUserDao {

    User queryUserInfoById(Long id);

}
