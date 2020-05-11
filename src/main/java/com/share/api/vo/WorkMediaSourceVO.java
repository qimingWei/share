package com.share.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author：weiqiming
 * @Description：媒体资源 视图对象
 * @Date：2020/5/7 14:12
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "媒体视图对象")
public class WorkMediaSourceVO {

    private String object_id;

    private String bucket_name;

    private String file_name;

    private String object_name;

    private String content_type;

    private String description;

    private Integer isDel;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    
}
