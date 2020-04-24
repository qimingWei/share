package com.share.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "WorkMediaSourceCreateDTO")
public class WorkMediaSourceCreateDTO {

    private String title;

    private String description;

    private String baseUrl;

    private String playUrl;

    private Integer isEnable = 1;

    private Integer isDel = 0;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime updateTime = LocalDateTime.now();

}
