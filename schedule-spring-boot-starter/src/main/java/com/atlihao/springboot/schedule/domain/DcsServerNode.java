package com.atlihao.springboot.schedule.domain;

public class DcsServerNode {

    //任务服务ID；  工程名称En
    private String schedulerServerId;
    //任务服务名称；工程名称Ch
    private String schedulerServerName;

    public DcsServerNode(String schedulerServerId, String schedulerServerName) {
        this.schedulerServerId = schedulerServerId;
        this.schedulerServerName = schedulerServerName;
    }

    public String getSchedulerServerId() {
        return schedulerServerId;
    }

    public void setSchedulerServerId(String schedulerServerId) {
        this.schedulerServerId = schedulerServerId;
    }

    public String getSchedulerServerName() {
        return schedulerServerName;
    }

    public void setSchedulerServerName(String schedulerServerName) {
        this.schedulerServerName = schedulerServerName;
    }
}
