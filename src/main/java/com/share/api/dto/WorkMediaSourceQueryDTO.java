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
public class WorkMediaSourceQueryDTO extends PageDtoBase{

    private Long id;

    private String title;

    private String description;

    private String baseUrl;

    private String playUrl;

    private Integer isEnable = 1;

    private Integer isDel = 0;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime updateTime = LocalDateTime.now();

}
