package com.share.api.minio.controller;

import com.buddy.sds.common.RestApiResponse;
import com.share.api.minio.service.MinioBucketService;
import io.minio.Result;
import io.minio.messages.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：weiqiming
 * @Description： minioBucket 请求处理
 * @Date： 2020/5/9 11:30
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/minioBucket")
@Api(value = "minio存储桶管理", tags = {"minio存储桶管理"})
public class MinioBucketController {

    private final MinioBucketService minioBucketService;

    @PostMapping("/listBuckets")
    @ApiOperation(value = "查询存储桶列表", httpMethod = "POST", notes = "查询存储桶列表")
    public RestApiResponse listBuckets() {
        return minioBucketService.listBuckets();
    }

    @PostMapping("/makeBucket")
    @ApiOperation(value = "创建存储桶", httpMethod = "POST", notes = "创建存储桶")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "bucketName", value = "存储桶名称", dataType = "String", required = true, paramType = "query")
    })
    public RestApiResponse makeBucket(@RequestParam("bucketName") String bucketName) {
        return minioBucketService.makeBucket(bucketName);
    }

    @PostMapping("/removeBucket")
    @ApiOperation(value = "移除存储桶", httpMethod = "POST", notes = "移除存储桶")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bucketName", value = "存储桶名称", dataType = "String", required = true, paramType = "query")
    })
    public RestApiResponse removeBucket(String bucketName) {
        return minioBucketService.removeBucket(bucketName);
    }

    @PostMapping("/listObjects")
    @ApiOperation(value = "存储桶内容列表", httpMethod = "POST", notes = "存储桶内容列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bucketName", value = "存储桶名称", dataType = "String", required = true, paramType = "query")
    })
    public Iterable<Result<Item>> listObjects(String bucketName) {
        return minioBucketService.listObjects(bucketName);
    }

}
