package com.yuqi.shard.config;

public class JdbcContextHolder {

    private final static ThreadLocal<String> local = new ThreadLocal<>();

    public static void putDataSource(String name){
        local.set(name);
    }

    public static String getDataSource(){
        return local.get();
    }

    public static void removeDataSource(){
        local.remove();
    }
}
