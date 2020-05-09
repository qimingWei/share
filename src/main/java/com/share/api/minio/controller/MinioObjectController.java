package com.share.api.minio.controller;

import com.buddy.sds.common.RestApiResponse;
import com.share.api.minio.service.MinioObjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author：weiqiming
 * @Description： minioBucket 请求处理
 * @Date： 2020/5/9 11:30
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/minioObject")
@Api(value = "minio存储对象管理", tags = {"minio存储对象管理"})
public class MinioObjectController {

    private final MinioObjectService minioObjectService;

    @GetMapping("/downloadObject")
    @ApiOperation(value = "下载存储对象", httpMethod = "POST", notes = "下载存储对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bucketName", value = "存储桶名称", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "objectName", value = "对象名称", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "fileName", value = "下载的文件名", dataType = "String", required = true, paramType = "query")
    })
    public void getObject(@RequestParam("bucketName") String bucketName, @RequestParam("objectName") String objectName, @RequestParam("fileName") String fileName) {
        minioObjectService.getObject(bucketName, objectName, fileName);
    }

    @PostMapping("putObject")
    @ApiOperation(value = "上传存储对象", httpMethod = "POST", notes = "上传存储对象")
    public RestApiResponse putObject(MultipartFile file) {
        return minioObjectService.putObject(file);
    }

}
