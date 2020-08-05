package com.yuqi.shard;

import com.yuqi.shard.test.EnableAAA;
import com.yuqi.shard.config.DataSourceConfig;
import com.yuqi.shard.controller.QueryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Import(value = {DataSourceConfig.class})
@EnableAAA(basePackageClass = {QueryController.class})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


