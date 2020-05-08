package com.share.api.minio.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    @ApiModelProperty(value = "minio服务器地址")
    private String url;

    @ApiModelProperty(value = "minio 用户名")
    private String accessKey;

    @ApiModelProperty(value = "minio 密码")
    private String secretKey;
}
