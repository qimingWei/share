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
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "权限管理", tags = {"权限管理"})
@RestController
@RequestMapping("/shareAuth")
@RequiredArgsConstructor
public class RbacController {

    private final SystemUserController systemUserController;
    private final VerificationCodeService verificationCodeService;

    @ApiOperation(value = "更新用户信息", httpMethod = "POST", notes = "更新用户信息")
    @PostMapping("/updateUser")
    public RestApiResponse updateUser(@RequestBody SystemUserDTO systemUserDTO) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDTO, systemUser);
        return systemUserController.updateUser(systemUser, true);
    }

    @ApiOperation(value = "注册用户", httpMethod = "POST", notes = "注册用户")
    @PostMapping("/addUser")
    public RestApiResponse addUser(@RequestBody SystemUserDTO systemUser) {
        RestApiResponse restApiResponse = verificationCodeService.checkVerificationCode(systemUser);
        if(0 == restApiResponse.getStatus()){
            String departmentId = "52dd8bec0cf189bda162d9279d386c2b";
            systemUser.setUserDepartment(departmentId);
            restApiResponse = systemUserController.addUser(systemUser);
        }
        return restApiResponse;
    }

    @ApiOperation(value = "获取验证码", httpMethod = "POST", notes = "获取验证码")
    @PostMapping("/getVerificationCode")
    public RestApiResponse getVerificationCode(@RequestBody SystemUserDTO systemUser) {
        int status = 0;
        String message;
        try {
            verificationCodeService.sendVerificationCodeByEmail(systemUser);
            message = "已发送，请登录邮箱查看验证码";
        } catch (Exception e) {
            status = -1;
            message = "获取验证码失败";
            e.printStackTrace();
        }
        return new RestApiResponse(status, message, null);
    }

    @ApiOperation(value = "获取验证码", httpMethod = "POST", notes = "获取验证码")
    @PostMapping("/checkVerificationCode")
    public RestApiResponse checkVerificationCode(@RequestBody SystemUserDTO systemUser) {
        return verificationCodeService.checkVerificationCode(systemUser);
    }

}

