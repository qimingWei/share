package com.share.api.controller;


import com.buddy.sds.auth.support.controller.SystemUserController;
import com.buddy.sds.auth.support.entity.SystemUser;
import com.buddy.sds.common.RestApiResponse;
import com.share.api.dto.WorkUpdateDTO;
import com.share.api.vo.WorkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "权限管理", tags = {"权限管理"})
@RestController
@RequestMapping("/rbac")
@Slf4j
@RequiredArgsConstructor
public class RbacController {

    // private SystemUserController systemUserController;

    @ApiOperation(value = "更新用户信息", httpMethod = "POST", notes = "更新用户信息")
    @PostMapping("/updateUser")
    public RestApiResponse updateUser(@RequestBody SystemUser systemUser, @RequestParam("updatePassword") boolean updatePassword, @RequestParam("SMSInfo") String SMSInfo) {
        if(updatePassword){
            // TODO 修改密码则需要短信服务验证
            // systemUserController.updateUser(systemUser, updatePassword);
        }else{
            // TODO 修改基本信息则直接调用修改用户信息接口
            // systemUserController.updateUser(systemUser, updatePassword);
        }
        return null;
        // return systemUserController.updateUser(systemUser, updatePassword);
    }
}

