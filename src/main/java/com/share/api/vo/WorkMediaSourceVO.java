package com.share.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "WorkMediaSource视图对象")
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
