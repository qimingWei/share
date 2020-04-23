package com.share.api.dto;

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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "WorkQueryDTO")
public class WorkQueryDTO extends PageDtoBase {

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
