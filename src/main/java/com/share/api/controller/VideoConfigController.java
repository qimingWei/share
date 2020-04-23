package com.share.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiqiming
 */
@RestController
@RequestMapping("/videoConfig")
@Api(value = "视频流配置", tags = {"视频流配置"})
@Slf4j
public class VideoConfigController {

    private static final String appName ="AppName";
    private static final String streamName ="StreamName";
    private static final String putRealmName ="put.aliyunlive.com";
    private static final String playRealmName ="play.aliyunlive.com";

    @ApiOperation("获取视频流播放地址")
    @PostMapping
    public String getPlayVideoUrl(){
        return playRealmName + appName + streamName;
    }

}
