package com.share.api.minio.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "Minio存储对象")
public class MinioObject {

    private String ETag;
    private String Key;
    private String LastModified;
    private Long Size;

}
