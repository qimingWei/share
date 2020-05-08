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
 * @Description：
 * @Date：2020/5/7 14:07
 **/
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
    private String coverImageUrl;

    @ApiModelProperty(value = "二维码地址")
    private String qrCodeGenerator;

    @ApiModelProperty(value = "页面内容")
    private String pages;

    @ApiModelProperty(value = "模板类型")
    private Integer workType;

    @ApiModelProperty(value = "是否发布")
    private Integer isPublish;

    @ApiModelProperty(value = "是否模板")
    private Integer isTemplate;

    @ApiModelProperty(value = "是否下载")
    private Integer isDownload;

    @ApiModelProperty(value = "直播源地址")
    private String liveUrlPush;

    @ApiModelProperty(value = "直播播放地址")
    private String liveUrlPlay;

    @ApiModelProperty(value = "媒体库元素ID")
    private Integer mediaSourceId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
