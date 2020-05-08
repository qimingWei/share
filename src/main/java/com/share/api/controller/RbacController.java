package com.share.api.controller;


import com.buddy.sds.auth.support.controller.SystemUserController;
import com.buddy.sds.auth.support.entity.SystemUser;
import com.buddy.sds.common.RestApiResponse;
import com.share.api.dto.SystemUserDTO;
import com.share.api.service.VerificationCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "权限管理", tags = {"权限管理"})
@RestController
@RequestMapping("/shareAuth")
@RequiredArgsConstructor
public class RbacController {

    private final SystemUserController systemUserController;
    private final VerificationCodeService verificationCodeService;

    @Value("${authDepartment}")
    private String authDepartment;

    @ApiOperation(value = "更新用户信息", httpMethod = "POST", notes = "更新用户信息")
    @PostMapping("/updateUser")
    public RestApiResponse updateUser(@RequestBody SystemUserDTO systemUserDTO) {
        // 获取当前登录用户ID
        SystemUser systemUserNow = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        systemUserDTO.setUserId(systemUserNow.getUserId());

        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDTO, systemUser);
        systemUser.setUserDepartment(authDepartment);
        if (1 == systemUserDTO.getUpdatePassword()) {
            RestApiResponse restApiResponse = verificationCodeService.checkVerificationCode(systemUserDTO);
            if (0 == restApiResponse.getStatus()) {
                restApiResponse = systemUserController.updateUser(systemUser, true);
            }
            return restApiResponse;
        } else {
            return systemUserController.updateUser(systemUser, false);
        }
    }

    @ApiOperation(value = "注册用户", httpMethod = "POST", notes = "注册用户")
    @PostMapping("/addUser")
    public RestApiResponse addUser(@RequestBody SystemUserDTO systemUser) {
        RestApiResponse restApiResponse = verificationCodeService.checkVerificationCode(systemUser);
        if (0 == restApiResponse.getStatus()) {
            systemUser.setUserDepartment(authDepartment);
            systemUser.setEmail(systemUser.getCheckEmail());
            restApiResponse = systemUserController.addUser(systemUser);
        }
        return restApiResponse;
    }

    @ApiOperation(value = "获取验证码", httpMethod = "POST", notes = "获取验证码")
    @PostMapping("/getVerificationCode")
    public RestApiResponse getVerificationCode(@RequestBody SystemUserDTO systemUser) {
        return verificationCodeService.sendVerificationCodeByEmail(systemUser);
    }

    @ApiOperation(value = "获取验证码", httpMethod = "POST", notes = "获取验证码")
    @PostMapping("/checkVerificationCode")
    public RestApiResponse checkVerificationCode(@RequestBody SystemUserDTO systemUser) {
        return verificationCodeService.checkVerificationCode(systemUser);
    }

}

