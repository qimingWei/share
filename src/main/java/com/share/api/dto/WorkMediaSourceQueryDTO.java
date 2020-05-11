package com.share.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author：weiqiming
 * @Description：媒体资源 查询DTO
 * @Date：2020/5/7 14:06
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WorkMediaSourceQueryDTO")
public class WorkMediaSourceQueryDTO extends PageDtoBase {

    private String objectId;

    private String bucketName;

    private String fileName;

    private String objectName;

    private String contentType;

    private Integer fileSize;

    private String description;

    private Integer isDel;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
