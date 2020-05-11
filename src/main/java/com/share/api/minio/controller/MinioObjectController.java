package com.share.api.minio.controller;

import com.buddy.sds.common.RestApiResponse;
import com.share.api.minio.service.MinioObjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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

    @GetMapping("/getObject")
    @ApiOperation(value = "下载存储对象", httpMethod = "POST", notes = "下载存储对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectId", value = "存储对象ID", dataType = "String", required = true, paramType = "query")
    })
    public ResponseEntity<InputStreamResource> getObject(@RequestParam("objectId") String objectId) {
        return minioObjectService.getObject(objectId);
    }

    @PostMapping("/putObject")
    @ApiOperation(value = "上传存储对象", httpMethod = "POST", notes = "上传存储对象")
    public RestApiResponse putObject(MultipartFile file, @RequestParam(value = "fileName") String fileName) {
        return minioObjectService.putObject(file, fileName);
    }

}
