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
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "WorkQueryDTO")
public class WorkUpdateDTO {

    private String title;

    private String description;

    private String cover_image_url;

    private String qrCodeGenerator;

    private List<Object> pages;

    private LocalDateTime updateTime = LocalDateTime.now();

    private Integer is_publish;

    private Integer is_template;

    private Integer is_download;
}
