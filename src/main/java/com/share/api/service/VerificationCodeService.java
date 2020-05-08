package com.share.api.service;

import com.buddy.sds.common.RestApiResponse;
import com.share.api.dto.SystemUserDTO;

/**
 * @Author：weiqiming
 * @Description：邮箱功能 工具类
 * @Date：2020/5/7 14:12
 **/
public interface VerificationCodeService {

    /**
     * @Author：weiqiming
     * @Description： 验证码功能 通过邮箱获取验证码
     * @Date： 2020/5/8 13:51
     * @Param： [systemUser]
     * @return： void
     **/
    void sendVerificationCodeByEmail(SystemUserDTO systemUser);

    /**
     * @Author：weiqiming
     * @Description： 验证码功能 验证账号和验证码判断验证码有效性
     * @Date： 2020/5/8 13:52
     * @Param： [systemUser]
     * @return： com.buddy.sds.common.RestApiResponse
     **/
    RestApiResponse checkVerificationCode(SystemUserDTO systemUser);
}
