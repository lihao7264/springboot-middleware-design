package com.atlihao.springboot.rpc.config.spring.bean;

import com.alibaba.fastjson.JSON;
import com.atlihao.springboot.rpc.config.ProviderConfig;
import com.atlihao.springboot.rpc.domain.LocalServerInfo;
import com.atlihao.springboot.rpc.domain.RpcProviderConfig;
import com.atlihao.springboot.rpc.registry.RedisRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(ProviderBean.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RpcProviderConfig rpcProviderConfig = new RpcProviderConfig();
        rpcProviderConfig.setNozzle(nozzle);
        rpcProviderConfig.setRef(ref);
        rpcProviderConfig.setAlias(alias);
        rpcProviderConfig.setHost(LocalServerInfo.LOCAL_HOST);
        rpcProviderConfig.setPort(LocalServerInfo.LOCAL_PORT);
        // 注册生产者
        long count = RedisRegistryCenter.registryProvider(nozzle, alias, JSON.toJSONString(rpcProviderConfig));
        logger.info("注册生产者：{} {} {}", nozzle, alias, count);
    }

}
