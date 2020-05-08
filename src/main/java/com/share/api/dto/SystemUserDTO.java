package com.share.api.dto;

import com.buddy.sds.auth.support.entity.SystemUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author：weiqiming
 * @Description：用户管理DTO
 * @Date：2020/5/7 14:03
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SystemUserDTO")
public class SystemUserDTO extends SystemUser {

    @ApiModelProperty(value = "验证邮箱")
    private String checkEmail;

    @ApiModelProperty(value = "验证手机")
    private String checkMobile;

    @ApiModelProperty(value = "验证码")
    private String verificationCode;
}
