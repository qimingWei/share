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

    private Long id;

    private String title;

    private String description;

    private String cover_image_url;

    private String qrCodeGenerator;

    private List<Object> pages;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer is_publish;

    private Integer is_template;

    private Integer is_download;
}
