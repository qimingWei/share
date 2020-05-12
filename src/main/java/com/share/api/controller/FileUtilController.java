package com.share.api.controller;

import com.share.api.util.QRCodeGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author  weiqiming
 * @Description  文件上传
 * @Date  2020/5/12 13:59
**/
@Slf4j
@Controller
@RequestMapping("/fileUtil")
@Api(value = "文件功能", tags = {"文件功能"})
public class FileUtilController {

    @Value("${upload-path}")
    public String uploadPath;

    @ApiOperation(value = "生成二维码", httpMethod = "POST", notes = "生成二维码，默认宽高：100 * 100")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", value = "文本信息", dataType = "String", required = true, paramType = "upload"),
            @ApiImplicitParam(name = "width", value = "宽", dataType = "int", paramType = "upload"),
            @ApiImplicitParam(name = "width", value = "高", dataType = "int", paramType = "upload")
    })
    @PostMapping("/QRCodeGenerator")
    public String QRCodeGenerator(@RequestParam("msg") String msg, @RequestParam(value = "width", defaultValue = "100") int width, @RequestParam(value = "height", defaultValue = "100") int height) {
        return QRCodeGenerator.getBarCode(msg, width, height, uploadPath);
    }

    @RequestMapping("/uploadPage")
    public String uploadPage() {
        return "updateload";
    }

}
