package com.share.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：weiqiming
 * @Description：分页基类
 * @Date：2020/5/7 14:01
 **/
@Data
public class PageDtoBase {
    /**
     * 每页行数
     */
    @ApiModelProperty(value = "每页行数")
    protected Integer size = 10;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    protected Integer current = 1;


    public void setSize(Integer size) {
        if (size != null) {
            this.size = size;
        }
    }

    public void setCurrent(Integer current) {
        if (current != null) {
            this.current = current;
        }
    }
}
