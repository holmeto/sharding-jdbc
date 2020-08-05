package com.yuqi.shard.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static volatile DynamicDataSource instance;

    private static final byte[] lock = new byte[0];

    private static Map<Object, Object> dataSourceMap = new HashMap<>();

    public DynamicDataSource() {
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return JdbcContextHolder.getDataSource();
    }

    public static DynamicDataSource getInstance() {
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    return new DynamicDataSource();
                }
            }
        }
        return instance;
    }
}
