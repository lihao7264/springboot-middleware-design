package com.atlihao.springboot.test.infrastructure.po;

import com.atlihao.springboot.db.router.DBRouterBase;

import java.util.Date;

public class User extends DBRouterBase {

    private Long id;
    // 用户ID
    private String userId;
    // 昵称
    private String userNickName;
    // 头像
    private String userHead;
    // 密码
    private String userPassword;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
