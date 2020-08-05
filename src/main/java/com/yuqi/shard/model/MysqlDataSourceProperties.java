package com.yuqi.shard.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="mysql")
public class MysqlDataSourceProperties {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    public String getUrl() {
        return url;
    }

    public MysqlDataSourceProperties setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MysqlDataSourceProperties setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MysqlDataSourceProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public MysqlDataSourceProperties setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }
}
