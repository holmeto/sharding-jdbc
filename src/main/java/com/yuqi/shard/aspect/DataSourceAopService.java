package com.yuqi.shard.aspect;

import com.yuqi.shard.config.JdbcContextHolder;
import com.yuqi.shard.dao.mapper.OrderMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAopService {

    @Resource
    private OrderMapper orderMapper;

    @Pointcut("execution(* com.yuqi.shard.dao.mapper..*.*(..))")
    public void dataSourcePointCut() {
        System.out.println("这是一个切换数据源的切面");
    }

    @Pointcut("execution(* com.yuqi.shard.dao.impl..*.*(..))")
    public void dataSourcePointCut2() {
        System.out.println("这是一个切换数据源的切面2");
    }

    @Pointcut("dataSourcePointCut() || dataSourcePointCut2()")
    public void dataSourcePointCut3() {
        System.out.println("这是一个切换数据源的切面3");
    }

    @Before("dataSourcePointCut()")
    private void dbBefore(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?> clazz = target.getClass();
        Class<?>[] paramTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz.getMethod(method, paramTypes);
            if (m.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                JdbcContextHolder.putDataSource(data.value());
                System.out.println("当前数据库已切换至：" + data.value());
            } else {
                JdbcContextHolder.putDataSource("dataSourceMysql");
                System.out.println("当前数据库已切换至: dataSourceMysql");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("恢复数据源至： dataSourceMysql");
        JdbcContextHolder.putDataSource("dataSourceMysql");
    }


}
