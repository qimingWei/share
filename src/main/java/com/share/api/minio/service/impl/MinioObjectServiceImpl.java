package com.share.api.minio.service.impl;

import com.buddy.sds.common.RestApiResponse;
import com.share.api.minio.config.MinioAutoConfiguration;
import com.share.api.minio.service.MinioObjectService;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author：weiqiming
 * @Description： Minio文件对象操作
 * @Date： 2020/5/9 15:41
 **/
@Service
@RequiredArgsConstructor
public class MinioObjectServiceImpl implements MinioObjectService {

    private final MinioAutoConfiguration minioAutoConfiguration;


    /**
     * @Author：weiqiming
     * @Description： 下载并将文件保存到本地。
     * @Date： 2020/5/9 15:54
     * @Param： [bucketName, objectName, fileName]
     * @return： void
     */
    @Override
    public void getObject(String bucketName, String objectName, String fileName) {
        MinioClient minioClient = minioAutoConfiguration.minioClient();
        try {
            // 调用statObject()来判断对象是否存在。
            // 如果不存在, statObject()抛出异常,
            // 否则则代表对象存在。
            minioClient.statObject(bucketName, objectName);
            // 读取Range
            // headerMap.put("Range", "bytes=" + offset + "-" + (offset + length - 1L));
            InputStream stream = minioClient.getObject("mybucket", "myobject");
            byte[] buf = new byte[16384];
            int flag = 0;
            int bytesRead;
            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                if (flag == 0) {
                    flag = 1;
                    String contentType = new Tika().detect(buf);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author：weiqiming
     * @Description： 上传存储对象
     * @Date： 2020/5/9 17:28
     * @Param： [file]
     * @return： com.buddy.sds.common.RestApiResponse
     */
    @Override
    public RestApiResponse putObject(MultipartFile file) {
        int status = 0;
        String message;
        MinioClient minioClient = minioAutoConfiguration.minioClient();
        try {
            InputStream stream = file.getInputStream();
            minioClient.putObject("images", "myobject", stream, stream.available(), "application/octet-stream");
            stream.close();
            message = "文件上传成功";
        } catch (Exception e) {
            status = -1;
            message = "上传失败，请联系管理员";
            e.printStackTrace();
        }
        return new RestApiResponse(status, message, null);
    }
}
