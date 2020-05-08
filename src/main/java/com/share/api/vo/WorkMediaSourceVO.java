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
    private Long id;

    private String title;

    private String description;

    private String baseUrl;

    private String playUrl;

    private Integer isEnable;

    private Integer isDel;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
