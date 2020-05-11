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

/**
 * @Author：weiqiming
 * @Description：媒体资源 实体对象
 * @Date：2020/5/7 14:07
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "媒体对象")
public class WorkMediaSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对象ID")
    private String object_id;

    @ApiModelProperty(value = "上传文件名")
    private String file_name;

    @ApiModelProperty(value = "存储桶名称")
    private String bucket_name;

    @ApiModelProperty(value = "对象地址")
    private String object_name;

    @ApiModelProperty(value = "对象描述")
    private String description;

    @ApiModelProperty(value = "内容类型")
    private String content_type;

    @ApiModelProperty(value = "是否删除（0：未删除；1：已删除）")
    private Integer isDel;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
