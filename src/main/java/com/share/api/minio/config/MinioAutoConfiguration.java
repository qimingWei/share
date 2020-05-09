package com.share.api.minio.config;

import com.share.api.minio.entity.MinioProperties;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableConfigurationProperties(MinioProperties.class)
public class MinioAutoConfiguration {
    private final MinioProperties minioProperties;

    @Bean
    @ConditionalOnProperty(prefix = "minio", name = "url", havingValue = "true")
    public MinioClient minioClient() {
        try {
            return new MinioClient(
                    minioProperties.getUrl(),
                    minioProperties.getAccessKey(),
                    minioProperties.getSecretKey()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
