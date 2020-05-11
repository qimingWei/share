package com.share.api.controller;

import cn.hutool.core.util.IdUtil;
import com.share.api.exception.UnifiedException;
import com.share.api.util.QRCodeGenerator;
import com.share.api.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：weiqiming
 * @Description：文件上传
 * @Date：2020/5/7 13:57
 **/
@Slf4j
@Controller
@RequestMapping("/fileUtil")
@Api(value = "文件功能", tags = {"文件功能"})
public class FileUtilController {

    @Value("${upload-path}")
    public String uploadPath;

    @ApiOperation(value = "文件上传", httpMethod = "POST", notes = "普通文件上传功能，上传地址：${upload-path}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件上传", dataType = "MultipartFile", required = true, paramType = "upload")
    })
    @PostMapping("/upload")
    public List<FileVO> upload(@Validated @NotNull MultipartFile files) {
        String originalFilename = files.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String key = IdUtil.fastSimpleUUID() + "." + suffix;
        try {
            files.transferTo(new File(uploadPath + key));
        } catch (IOException e) {
            throw new UnifiedException("文件上传失败");
        }
        return new ArrayList<FileVO>() {{
            add(new FileVO() {{
                setUrl(key);
            }});
        }};
    }

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
