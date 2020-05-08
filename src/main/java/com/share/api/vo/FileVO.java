package com.share.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author：weiqiming
 * @Description：文件 视图对象
 * @Date：2020/5/7 14:10
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "文件视图对象")
public class FileVO {
    private String url;
}
