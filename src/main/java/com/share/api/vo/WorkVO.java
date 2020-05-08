package com.share.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WeiHongBin
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "Work视图对象")
public class WorkVO {

    private String title;

    private String description;

    private String coverImageUrl;

    private String qrCodeGenerator;

    private List<Object> pages;

    private Integer workType;

    private Integer isPublish;

    private Integer isTemplate;

    private Integer isDownload;

    private String liveUrlPush;

    private String liveUrlPlay;

    private Integer mediaSourceId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
