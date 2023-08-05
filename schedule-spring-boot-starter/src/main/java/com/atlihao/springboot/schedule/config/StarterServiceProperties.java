package com.atlihao.springboot.schedule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("schedule")
public class StarterServiceProperties {

    // zookeeper服务地址；x.x.x.x:2181
    private String zkAddress;
    // 任务服务ID；  工程名称En
    private String schedulerServerId;
    // 任务服务名称；工程名称Ch
    private String schedulerServerName;

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
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
