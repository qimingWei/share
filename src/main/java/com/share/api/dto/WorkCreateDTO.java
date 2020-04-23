package com.share.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "WorkCreateDTO")
public class WorkCreateDTO {

    private String title;

    private String description;

    private String cover_image_url;

    private String qrCodeGenerator;

    private List<Object> pages;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime updateTime = LocalDateTime.now();

    private Integer is_publish;

    private Integer is_template;

    private Integer is_download;
}
