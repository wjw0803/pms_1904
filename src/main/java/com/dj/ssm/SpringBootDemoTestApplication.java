package com.dj.ssm;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.dj.ssm.mapper")
@ServletComponentScan("com.dj.ssm.config")
public class SpringBootDemoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoTestApplication.class, args);
    }

}
