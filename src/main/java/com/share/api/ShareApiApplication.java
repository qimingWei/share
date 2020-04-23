package com.share.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 鲁班H5 api启动类
 *
 * @author WeiHongBin
 */
// @SpringBootApplication(scanBasePackages = {"com.luban.api.lubanapi","com.buddy.sds"})
// @MapperScan(basePackages = {"com.luban.api.lubanapi","com.buddy.sds"},annotationClass = Mapper.class)
@SpringBootApplication
public class ShareApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareApiApplication.class, args);
    }

}
