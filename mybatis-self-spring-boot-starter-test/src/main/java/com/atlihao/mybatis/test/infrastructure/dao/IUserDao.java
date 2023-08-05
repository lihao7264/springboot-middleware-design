package com.atlihao.mybatis.test.infrastructure.dao;

import com.atlihao.mybatis.test.infrastructure.po.User;

public interface IUserDao {

    User queryUserInfoById(Long id);

}
