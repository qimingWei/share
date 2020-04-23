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
 * <p>
 *
 * </p>
 *
 * @author WeiHongBin
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "Work对象")
public class Work implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "封面图片")
    private String cover_image_url;

    @ApiModelProperty(value = "页面内容")
    private String pages;

    @ApiModelProperty(value = "是否发布")
    private int is_publish;

    @ApiModelProperty(value = "是否模板")
    private int is_template;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
