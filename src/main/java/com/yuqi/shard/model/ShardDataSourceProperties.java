package com.yuqi.shard.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix="sharding.jdbc")
public class ShardDataSourceProperties {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private String url0;

    private String username0;

    private String password0;

    private String url1;

    private String username1;

    private String password1;

    private String filters;

    private int maxActive;

    private int initialSize;

    private int maxWait;

    private int minIdle;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private boolean removeAbandoned;

    private int removeAbandonedTimeout;

    private boolean logAbandoned;

    private List<String> connectionInitSqls;

    public String getDriverClassName() {
        return driverClassName;
    }

    public ShardDataSourceProperties setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ShardDataSourceProperties setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ShardDataSourceProperties setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ShardDataSourceProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUrl0() {
        return url0;
    }

    public ShardDataSourceProperties setUrl0(String url0) {
        this.url0 = url0;
        return this;
    }

    public String getUsername0() {
        return username0;
    }

    public ShardDataSourceProperties setUsername0(String username0) {
        this.username0 = username0;
        return this;
    }

    public String getPassword0() {
        return password0;
    }

    public ShardDataSourceProperties setPassword0(String password0) {
        this.password0 = password0;
        return this;
    }

    public String getUrl1() {
        return url1;
    }

    public ShardDataSourceProperties setUrl1(String url1) {
        this.url1 = url1;
        return this;
    }

    public String getUsername1() {
        return username1;
    }

    public ShardDataSourceProperties setUsername1(String username1) {
        this.username1 = username1;
        return this;
    }

    public String getPassword1() {
        return password1;
    }

    public ShardDataSourceProperties setPassword1(String password1) {
        this.password1 = password1;
        return this;
    }

    public String getFilters() {
        return filters;
    }

    public ShardDataSourceProperties setFilters(String filters) {
        this.filters = filters;
        return this;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public ShardDataSourceProperties setMaxActive(int maxActive) {
        this.maxActive = maxActive;
        return this;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public ShardDataSourceProperties setInitialSize(int initialSize) {
        this.initialSize = initialSize;
        return this;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public ShardDataSourceProperties setMaxWait(int maxWait) {
        this.maxWait = maxWait;
        return this;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public ShardDataSourceProperties setMinIdle(int minIdle) {
        this.minIdle = minIdle;
        return this;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public ShardDataSourceProperties setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        return this;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public ShardDataSourceProperties setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        return this;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public ShardDataSourceProperties setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
        return this;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public ShardDataSourceProperties setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
        return this;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public ShardDataSourceProperties setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
        return this;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public ShardDataSourceProperties setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
        return this;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public ShardDataSourceProperties setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
        return this;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public ShardDataSourceProperties setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
        return this;
    }

    public boolean isRemoveAbandoned() {
        return removeAbandoned;
    }

    public ShardDataSourceProperties setRemoveAbandoned(boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
        return this;
    }

    public int getRemoveAbandonedTimeout() {
        return removeAbandonedTimeout;
    }

    public ShardDataSourceProperties setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
        return this;
    }

    public boolean isLogAbandoned() {
        return logAbandoned;
    }

    public ShardDataSourceProperties setLogAbandoned(boolean logAbandoned) {
        this.logAbandoned = logAbandoned;
        return this;
    }

    public List<String> getConnectionInitSqls() {
        return connectionInitSqls;
    }

    public ShardDataSourceProperties setConnectionInitSqls(List<String> connectionInitSqls) {
        this.connectionInitSqls = connectionInitSqls;
        return this;
    }
}