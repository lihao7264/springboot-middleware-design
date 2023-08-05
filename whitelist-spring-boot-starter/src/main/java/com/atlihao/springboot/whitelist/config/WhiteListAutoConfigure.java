package com.atlihao.springboot.whitelist.config;

import com.atlihao.springboot.whitelist.AccessLogFilter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: lihao726726
 * @CreateDate: 2023/7/11 11:03 上午
 * @UpdateUser: lihao726726
 * @UpdateDate: 2023/7/11 11:03 上午
 * @Version: 1.0.0
 */
@Configuration
@ConditionalOnClass(WhiteListProperties.class)
@EnableConfigurationProperties(WhiteListProperties.class)
@ConditionalOnProperty(prefix = "atlihao.whitelist", value = "enabled", matchIfMissing = false)
public class WhiteListAutoConfigure {

    @Bean("whiteListConfig")
    @ConditionalOnMissingBean
    public String whiteListConfig(WhiteListProperties properties) {
        return properties.getUsers();
    }


    @Bean("accessLogFilter")
    @ConditionalOnMissingBean(AccessLogFilter.class)
    public FilterRegistrationBean<AccessLogFilter> accessLogFilter() {
        FilterRegistrationBean<AccessLogFilter> bean = new FilterRegistrationBean<>();
        AccessLogFilter authFilter = new AccessLogFilter();
        bean.setFilter(authFilter);
        bean.addUrlPatterns("/*");
        return bean;
    }
}