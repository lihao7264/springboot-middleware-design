package com.atlihao.springboot.schedule.common;

import com.atlihao.springboot.schedule.domain.ExecOrder;
import com.atlihao.springboot.schedule.task.ScheduledTask;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constants {

    // 任务组；<beanName,List<ExecOrder>>
    public static final Map<String, List<ExecOrder>> execOrderMap = new ConcurrentHashMap<>();
    public static final Map<String, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    public static class Global {
        public static ApplicationContext applicationContext;
        public static final String LINE = "/";
        public static String CHARSET_NAME = "utf-8";
        // 定时任务执行线程池核心线程数
        public static int schedulePoolSize = 8;
        // 本机IP
        public static String ip;
        // zookeeper服务地址；x.x.x.x:2181
        public static String zkAddress;
        // 任务服务ID；  工程名称En
        public static String schedulerServerId;
        // 任务服务名称；工程名称Ch
        public static String schedulerServerName;
        // zk配置；client
        public static CuratorFramework client;
        // zk配置；根目录
        public static String path_root = "/schedule";
        public static String path_root_exec = path_root + "/exec";
        public static String path_root_server;
        public static String path_root_server_ip;
        // [结构标记]类名称
        public static String path_root_server_ip_clazz;
        // [结构标记]临时节点
        public static String path_root_server_ip_clazz_method;
        // [结构标记]永久节点
        public static String path_root_server_ip_clazz_method_status;
    }

    public static class InstructStatus {
        // 停止
        public final static Integer stop = 0;
        // 启动
        public final static Integer Start = 1;
        // 刷新
        public final static Integer Refresh = 2;
    }

}
