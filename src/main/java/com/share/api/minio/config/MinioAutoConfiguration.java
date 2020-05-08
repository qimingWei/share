package com.share.api.minio.config;

import com.share.api.minio.entity.MinioProperties;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AllArgsConstructor
@EnableConfigurationProperties(MinioProperties.class)
public class MinioAutoConfiguration {
    private final MinioProperties minioProperties;

    @Bean
    @ConditionalOnProperty(prefix = "minio", name = "url", havingValue = "true")
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(
                minioProperties.getUrl(),
                minioProperties.getAccessKey(),
                minioProperties.getSecretKey()
        );
    }
}
