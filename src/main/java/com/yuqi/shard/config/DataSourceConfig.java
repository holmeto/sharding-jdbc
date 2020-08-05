package com.yuqi.shard.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.yuqi.shard.dao.DaoPkg;
import com.yuqi.shard.model.MysqlDataSourceProperties;
import com.yuqi.shard.model.ShardDataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties({ShardDataSourceProperties.class, MysqlDataSourceProperties.class})
@MapperScan(basePackageClasses = {DaoPkg.class}, sqlSessionFactoryRef = "yuqiSqlSessionFactoryBean")
public class DataSourceConfig {

    @Autowired
    private ShardDataSourceProperties shardDataSourceProperties;

    @Autowired
    private MysqlDataSourceProperties mysqlDataSourceProperties;

    @Bean
    public SqlSessionFactory yuqiSqlSessionFactoryBean(@Autowired final DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.yuqi.shard.dao");
        Resource resource = new PathMatchingResourcePatternResolver()
                .getResource("classpath:/mybatis-config.xml");
        bean.setConfigLocation(resource);
        return bean.getObject();
    }

    private DruidDataSource parentDs() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(shardDataSourceProperties.getDriverClassName());
        ds.setUsername(shardDataSourceProperties.getUsername());
        ds.setUrl(shardDataSourceProperties.getUrl());
        ds.setPassword(shardDataSourceProperties.getPassword());
//        ds.setFilters(shardDataSourceProperties.getFilters());
        ds.setMaxActive(shardDataSourceProperties.getMaxActive());
        ds.setInitialSize(shardDataSourceProperties.getInitialSize());
        ds.setMaxWait(shardDataSourceProperties.getMaxWait());
        ds.setMinIdle(shardDataSourceProperties.getMinIdle());
        ds.setTimeBetweenEvictionRunsMillis(shardDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(shardDataSourceProperties.getMinEvictableIdleTimeMillis());
        ds.setValidationQuery(shardDataSourceProperties.getValidationQuery());
        ds.setTestWhileIdle(shardDataSourceProperties.isTestWhileIdle());
        ds.setTestOnBorrow(shardDataSourceProperties.isTestOnBorrow());
        ds.setTestOnReturn(shardDataSourceProperties.isTestOnReturn());
        ds.setPoolPreparedStatements(shardDataSourceProperties.isPoolPreparedStatements());
        ds.setMaxPoolPreparedStatementPerConnectionSize(
                shardDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        ds.setRemoveAbandoned(shardDataSourceProperties.isRemoveAbandoned());
        ds.setRemoveAbandonedTimeout(shardDataSourceProperties.getRemoveAbandonedTimeout());
        ds.setLogAbandoned(shardDataSourceProperties.isLogAbandoned());
        ds.setConnectionInitSqls(shardDataSourceProperties.getConnectionInitSqls());
        return ds;
    }

    private DataSource ds0() {
        DruidDataSource ds = parentDs();
        ds.setUsername(shardDataSourceProperties.getUsername0());
        ds.setUrl(shardDataSourceProperties.getUrl0());
        ds.setPassword(shardDataSourceProperties.getPassword0());
        return ds;
    }

    private DataSource ds1() {
        DruidDataSource ds = parentDs();
        ds.setUsername(shardDataSourceProperties.getUsername1());
        ds.setUrl(shardDataSourceProperties.getUrl1());
        ds.setPassword(shardDataSourceProperties.getPassword1());
        return ds;
    }

    @Bean("shardingDataSource")
    public DataSource config() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第 1 个数据源
        DataSource dataSource0 = ds0();
        // 配置第 2 个数据源
        DataSource dataSource1 = ds1();
        dataSourceMap.put("ds0", dataSource0);
        dataSourceMap.put("ds1", dataSource1);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order", "ds${0..1}.t_order_${0..1}");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 省略配置order_item表规则...
        // ...

        // 获取数据源对象
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
    }

    @Bean(name = "dataSourceMysql")
    public DataSource dataSourceMysql() {
        DruidDataSource datasource = parentDs();
        datasource.setUrl(mysqlDataSourceProperties.getUrl());
        datasource.setUsername(mysqlDataSourceProperties.getUsername());
        datasource.setPassword(mysqlDataSourceProperties.getPassword());
        datasource.setDriverClassName(mysqlDataSourceProperties.getDriverClassName());
        return datasource;
    }

    @Bean(name = "dynamicDataSource")  //将三个数据源装起来
    @Primary
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance(); //私有化构造了
        DataSource mysql = dataSourceMysql();
        dynamicDataSource.setDefaultTargetDataSource(mysql);
        DataSource shard = null;
        try {
            shard = config();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //配置多个数据源
        Map<Object, Object> map = new HashMap<>();
        map.put("shardingDataSource", shard);
        map.put("dataSourceMysql", mysql);
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }


}
