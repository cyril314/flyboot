package com.fit.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * flowable工作流的的配置
 *
 * @author Aim
 * @date 2017-12-02 23:18
 */
@Configuration
@ConfigurationProperties(prefix = FitFlowableProperties.FIT_FLOWABLE_DATASOURCE)
public class FitFlowableProperties {

    public static final String FIT_FLOWABLE_DATASOURCE = "fit.flowable.datasource";

    private String url_param = "autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

    /**
     * 默认多数据源的链接
     */
    private String url = "jdbc:mysql://127.0.0.1:3306/fly_flowable?" + url_param;

    /**
     * 默认多数据源的数据库账号
     */
    private String username = "root";

    /**
     * 默认多数据源的数据库密码
     */
    private String password = "root";

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
