package com.share.api.minio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddy.sds.common.RestApiResponse;
import com.share.api.dto.WorkMediaSourceCreateDTO;
import com.share.api.entity.WorkMediaSource;
import com.share.api.minio.config.MinioAutoConfiguration;
import com.share.api.minio.service.MinioObjectService;
import com.share.api.service.WorkMediaSourceService;
import com.share.api.util.Md5Util;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author：weiqiming
 * @Description： Minio文件对象操作
 * @Date： 2020/5/9 15:41
 **/
@Service
@RequiredArgsConstructor
public class MinioObjectServiceImpl implements MinioObjectService {

    private final MinioAutoConfiguration minioAutoConfiguration;
    private final WorkMediaSourceService workMediaSourceService;

    @Value("${uploadInfo.bucketName}")
    private String bucketName;

    /**
     * @Author：weiqiming
     * @Description： 下载并将文件保存到本地。
     * @Date： 2020/5/9 15:54
     * @Param： [bucketName, objectName, fileName]
     * @return： void
     */
    @Override
    public ResponseEntity<InputStreamResource> getObject(String objectId) {
        MinioClient minioClient = minioAutoConfiguration.minioClient();
        try {
            WorkMediaSource workMediaSource = workMediaSourceService.getOne(new QueryWrapper<WorkMediaSource>().eq("object_id", objectId));

            // 调用statObject()来判断对象是否存在。
            // 如果不存在, statObject()抛出异常,
            // 否则则代表对象存在。
            minioClient.statObject(workMediaSource.getBucketName(), workMediaSource.getObjectId());

            InputStream stream = minioClient.getObject(workMediaSource.getBucketName(), workMediaSource.getObjectId());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Last-Modified", workMediaSource.getCreateTime().toString());
            headers.add("ETag", workMediaSource.getObjectId());
            try {
                headers.add("Content-Disposition", "inline; filename=" + new String(workMediaSource.getFileName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            headers.setContentLength(workMediaSource.getFileSize());
            headers.setContentType(MediaType.parseMediaType(workMediaSource.getContentType()));
            return new ResponseEntity<>(new InputStreamResource(stream), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
    public RestApiResponse putObject(MultipartFile file, String fileName) {
        int status = 0;
        String message;
        String data = null;
        MinioClient minioClient = minioAutoConfiguration.minioClient();
        InputStream stream = null;
        try {

            stream = file.getInputStream();
            String contentType;
            contentType = new Tika().detect(stream);
            contentType = MediaType.parseMediaType(contentType).toString();
            stream.close();

            String objectId = Md5Util.getMD5String(String.valueOf(System.currentTimeMillis()));
            stream = file.getInputStream();
            int fileSize = stream.available();
            minioClient.putObject(bucketName, objectId, stream, fileSize, contentType);

            WorkMediaSourceCreateDTO mediaSourceCreateDTO = new WorkMediaSourceCreateDTO();
            mediaSourceCreateDTO.setObjectId(objectId);
            mediaSourceCreateDTO.setBucketName(bucketName);
            mediaSourceCreateDTO.setFileName(fileName);
            mediaSourceCreateDTO.setObjectId(objectId);
            mediaSourceCreateDTO.setContentType(contentType);
            mediaSourceCreateDTO.setFileSize(fileSize);
            workMediaSourceService.createWorkMediaSource(mediaSourceCreateDTO);

            message = "文件上传成功";
            data = objectId;
        } catch (Exception e) {
            status = -1;
            message = "上传失败，请联系管理员";
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new RestApiResponse(status, message, data);
    }
}
