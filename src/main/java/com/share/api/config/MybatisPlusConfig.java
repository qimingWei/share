package com.share.api.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.share.api.constant.PackageConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Author：weiqiming
 * @Description：myBatis 配置
 * @Date：2020/5/7 13:56
 **/
@EnableTransactionManagement
@Configuration
@MapperScan(PackageConstant.DAO_PACKAGE)
public class MybatisPlusConfig {

    // 分页插件 @Bean public PaginationInterceptor paginationInterceptor() { return new PaginationInterceptor(); }

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
