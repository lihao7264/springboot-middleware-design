package com.atlihao.springboot.test.infrastructure.dao;

import com.atlihao.springboot.db.router.annotation.DBRouter;
import com.atlihao.springboot.test.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {

    @DBRouter(key = "userId")
    User queryUserInfoByUserId(User req);

    @DBRouter(key = "userId")
    void insertUser(User req);

}
