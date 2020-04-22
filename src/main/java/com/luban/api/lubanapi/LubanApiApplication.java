package com.luban.api.lubanapi;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 鲁班H5 api启动类
 *
 * @author WeiHongBin
 */
@SpringBootApplication(scanBasePackages = {"com.luban.api.lubanapi","com.buddy.sds"})
@MapperScan(basePackages = {"com.luban.api.lubanapi","com.buddy.sds"},annotationClass = Mapper.class)
public class LubanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LubanApiApplication.class, args);
    }

}
