package com.share.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "WorkMediaSourceUpdateDTO")
public class WorkMediaSourceUpdateDTO {

    private Long id;

    private String title;

    private String description;

    private String baseUrl;

    private String playUrl;

    private Integer isEnable;

    private Integer isDel;

    private LocalDateTime updateTime = LocalDateTime.now();

}
