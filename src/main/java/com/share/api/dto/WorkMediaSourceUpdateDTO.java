package com.share.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author：weiqiming
 * @Description：媒体资源 修改DTO
 * @Date：2020/5/7 14:07
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "WorkMediaSourceUpdateDTO")
public class WorkMediaSourceUpdateDTO {

    private String object_id;

    private String bucket_name;

    private String file_name;

    private String object_name;

    private String content_type;

    private String description;

    private Integer isDel;

    private LocalDateTime updateTime = LocalDateTime.now();

}
