package com.luban.api.lubanapi.controller;

import cn.hutool.core.util.IdUtil;
import com.buddy.sds.auth.support.service.ISystemUserService;
import com.luban.api.lubanapi.exception.UnifiedException;
import com.luban.api.lubanapi.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weiqiming
 */
@RestController
@RequestMapping("/videoConfig")
@Api(value = "视频流配置", tags = {"视频流配置"})
@Slf4j
public class videoConfigController {

    private static final String appName ="AppName";
    private static final String streamName ="StreamName";
    private static final String putRealmName ="put.aliyunlive.com";
    private static final String playRealmName ="play.aliyunlive.com";

    @ApiOperation("获取视频流播放地址")
    @PostMapping
    public String getPlayVideoUrl(){
        StringBuilder returnStr = new StringBuilder();

        return returnStr.toString();
    }

}
