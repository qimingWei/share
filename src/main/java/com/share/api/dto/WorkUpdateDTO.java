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

    private List<Object> pages;

    private LocalDateTime updateTime = LocalDateTime.now();

    private int is_publish;

    private int is_template;
}
