package com.share.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "媒体对象")
public class WorkMediaSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "视频源地址")
    private String baseUrl;

    @ApiModelProperty(value = "视频播放地址")
    private String playUrl;

    @ApiModelProperty(value = "是否启用（0：未启用；1：已启用）")
    private Integer isEnable;

    @ApiModelProperty(value = "标题")
    private Integer isDel;

    @ApiModelProperty(value = "是否删除（0：未删除；1：已删除）")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
