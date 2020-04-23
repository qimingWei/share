package com.share.api.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import com.share.api.constant.PackageConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 文档 配置
 *
 * @author WeiHongBin
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUi
public class SwaggerConfiguration {


    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("默认接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage(PackageConstant.CONTROLLER_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("星分享 Api")
                .description("# 星分享 Api")
                .termsOfServiceUrl("XXX")
                .contact(new Contact("XXX", "XXX", "xXx@XxX.com"))
                .version("1.0")
                .build();
    }

}
