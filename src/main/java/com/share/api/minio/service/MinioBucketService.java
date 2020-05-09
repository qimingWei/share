package com.share.api.minio.service;

import com.buddy.sds.common.RestApiResponse;
import io.minio.Result;
import io.minio.messages.Item;

/**
 * @Author：weiqiming
 * @Description： Minio 存储桶操作
 * @Date： 2020/5/8 18:22
 **/
public interface MinioBucketService {

    /**
     * @Author：weiqiming
     * @Description： 创建存储桶
     * @Date： 2020/5/9 10:08
     * @Param： [bucketName]
     * @return： void
     **/
    RestApiResponse makeBucket(String bucketName);

    /**
     * @Author：weiqiming
     * @Description： 移除存储桶
     * @Date： 2020/5/9 10:33
     * @Param： [bucketName]
     * @return： void
     **/
    RestApiResponse removeBucket(String bucketName);

    /**
     * @Author：weiqiming
     * @Description： 查询存储桶集合
     * @Date： 2020/5/9 10:23
     * @Param： []
     * @return： List<Bucket>
     **/
    RestApiResponse listBuckets();

    /**
     * @Author：weiqiming
     * @Description： 列出某个存储桶中的所有对象
     * @Date： 2020/5/9 10:40
     * @Param： [bucketName, prefix 对象前缀 , recursive 是否递归查询]
     * @return： java.lang.Iterable<io.minio.Result < io.minio.messages.Item>>
     **/
    Iterable<Result<Item>> listObjects(String bucketName);

}
