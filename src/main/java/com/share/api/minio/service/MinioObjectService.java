package com.share.api.minio.service;

import com.buddy.sds.common.RestApiResponse;
import com.share.api.minio.config.MinioAutoConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author：weiqiming
 * @Description： Minio文件对象操作
 * @Date： 2020/5/9 15:41
 **/
public interface MinioObjectService {

    /**
     * @Author：weiqiming
     * @Description： 下载并将文件保存到本地。
     * @Date： 2020/5/9 15:54 
     * @Param： [bucketName, objectName, fileName]
     * @return： void
     **/
    void getObject(String bucketName, String objectName, String fileName);

    /**
     * @Author：weiqiming
     * @Description： 上传存储对象
     * @Date： 2020/5/9 17:28
     * @Param： [file]
     * @return： com.buddy.sds.common.RestApiResponse
     **/
    RestApiResponse putObject(MultipartFile file);
}
