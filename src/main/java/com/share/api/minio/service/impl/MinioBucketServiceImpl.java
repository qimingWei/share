package com.share.api.minio.service.impl;

import com.buddy.sds.common.RestApiResponse;
import com.share.api.minio.config.MinioAutoConfiguration;
import com.share.api.minio.service.MinioBucketService;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：weiqiming
 * @Description： Minio 存储桶操作
 * @Date： 2020/5/8 18:22
 **/
@Service
@RequiredArgsConstructor
public class MinioBucketServiceImpl implements MinioBucketService {

    private final MinioAutoConfiguration minioAutoConfiguration;

    @Override
    public RestApiResponse makeBucket(String bucketName) {
        int status = 0;
        String message;
        MinioClient minioClient = minioAutoConfiguration.minioClient();
        try {
            // 如存储桶不存在，创建之。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                status = -1;
                message = "存储桶已存在，请检查名称是否输入正确";
            } else {
                // 创建名为'my-bucketname'的存储桶。
                minioClient.makeBucket(bucketName);
                message = "创建成功";
            }

        } catch (Exception e) {
            status = -1;
            message = "存储桶创建异常，请联系管理员";
            e.printStackTrace();
        }
        return new RestApiResponse(status, message, null);
    }

    @Override
    public RestApiResponse removeBucket(String bucketName) {
        int status = 0;
        String message;
        MinioClient minioClient = minioAutoConfiguration.minioClient();
        try {
            // 删除之前先检查`my-bucket`是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                // 删除`my-bucketname`存储桶，注意，只有存储桶为空时才能删除成功。
                if (minioClient.listObjects(bucketName).iterator().hasNext()) {
                    status = -1;
                    message = "存储桶内存在内容，请清空内容后再进行移除操作";
                } else {
                    minioClient.removeBucket(bucketName);
                    message = "移除成功";
                }
            } else {
                status = -1;
                message = "存储桶不存在，请检查名称是否输入正确";
            }
        } catch (Exception e) {
            status = -1;
            message = "存储桶移除异常，请联系管理员";
            e.printStackTrace();
        }
        return new RestApiResponse(status, message, null);
    }

    @Override
    public RestApiResponse listBuckets() {
        int status = 0;
        String message;
        MinioClient minioClient = minioAutoConfiguration.minioClient();
        List<Bucket> bucketList = null;
        try {
            // 列出所有存储桶
            bucketList = minioClient.listBuckets();
            for (Bucket bucket : bucketList) {
                System.out.println(bucket.creationDate() + ", " + bucket.name());
            }
            message = "存储桶列表查询成功";
        } catch (Exception e) {
            status = -1;
            message = "存储桶列表查询异常，请联系管理员";
            e.printStackTrace();
        }
        return new RestApiResponse(status, message, bucketList);
    }

    @Override
    public Iterable<Result<Item>> listObjects(String bucketName) {
        MinioClient minioClient = minioAutoConfiguration.minioClient();
        Iterable<Result<Item>> myObjects = null;
        try {
            // 检查'mybucket'是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                // 列出'my-bucketname'里的对象
                myObjects = minioClient.listObjects(bucketName);
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myObjects;
    }

}
