package com.share.api.service.impl;

import com.buddy.sds.common.RestApiResponse;
import com.share.api.dto.SystemUserDTO;
import com.share.api.service.VerificationCodeService;
import com.share.api.util.DateUtils;
import com.share.api.util.EmailUtil;
import com.share.api.util.EnumSingleton;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Value("${verificationCode.timeout}")
    private Integer timeout;

    @Value("${verificationCode.maxCount}")
    private Integer maxCount;

    @Value("${mail.verificationCodeTemplate}")
    private String templateName;

    private final EmailUtil emailUtil;

    /**
     * @Author：weiqiming
     * @Description：验证码功能 通过邮箱获取验证码
     * @Date：2020/5/7 15:43
     * @Param：[emailAddr]
     * @return：void
     **/
    @Override
    public RestApiResponse sendVerificationCodeByEmail(SystemUserDTO systemUser) {
        int status = 0;
        String message;
        try{
            if (checkSendCount(systemUser.getCheckEmail())) {
                String emailAddr = systemUser.getCheckEmail();
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("verificationCode", creatVerificationCode(emailAddr));
                dataMap.put("date", DateUtils.getTodayDate(true));
                emailUtil.sendMail(emailAddr, "获取验证码", templateName, dataMap);
                message = "验证码已发送，请登录邮箱查看验证码";
            } else {
                status = -1;
                message = "超出发送次数，请稍后重试";
            }
        }
        catch (Exception e){
            status = -1;
            message = "发送验证码失败，请联系管理员";
            e.printStackTrace();
        }
        return new RestApiResponse(status, message, null);
    }

    /**
     * @Author：weiqiming
     * @Description： 验证验证码
     * @Date： 2020/5/8 10:32
     * @Param： [systemUser]
     * @return： com.buddy.sds.common.RestApiResponse
     **/
    @Override
    public RestApiResponse checkVerificationCode(SystemUserDTO systemUser) {
        int status = 0;
        String message;
        String emailAddr = systemUser.getCheckEmail();
        String verificationCodeValue = EnumSingleton.getVerificationCodeInstance().VerificationCode.get(emailAddr);
        String[] verificationCodeInfo = verificationCodeValue.split("-");
        String createCurrentTime = verificationCodeInfo[0];
        String verificationCode = verificationCodeInfo[1];
        if (checkTimeOut(createCurrentTime)) {
            status = -1;
            message = "验证码过期";
        } else {
            if (systemUser.getVerificationCode().equals(verificationCode)) {
                message = "验证通过";
            } else {
                status = -1;
                message = "验证码错误";
            }
        }
        return new RestApiResponse(status, message, null);
    }


    /**
     * @Author：weiqiming
     * @Description：邮箱功能 存储生成的邮箱验证码供验证使用。
     * @Date：2020/5/7 15:28
     * @Param： [verificationAccount]
     * @return：void
     **/
    private String creatVerificationCode(String verificationAccount) {
        String baseCurrentTime = String.valueOf(System.currentTimeMillis());
        String verificationCode = baseCurrentTime.substring(8);
        // 创建验证码的时间 - 通过当前时间获取的随机验证码
        String verificationCodeValue = baseCurrentTime + "-" + verificationCode;
        EnumSingleton.getVerificationCodeInstance().VerificationCode.put(verificationAccount, verificationCodeValue);
        return verificationCode;
    }

    /**
     * @Author：weiqiming
     * @Description：验证码功能 判断验证码是否过期
     * @Date：2020/5/7 17:31
     * @Param：[checkVal]
     * @return：boolean
     **/
    private boolean checkTimeOut(String checkVal) {
        boolean returnVal = false;
        long checkTime = Long.parseLong(checkVal);
        long nowTime = System.currentTimeMillis();
        long dValue = DateUtils.minus(nowTime, checkTime, "mm");
        if (timeout < dValue) {
            returnVal = true;
        }
        return returnVal;
    }

    /**
     * @Author：weiqiming
     * @Description： 判断当前请求验证的账号在一次有效期内是否超出请求次数
     * @Date： 2020/5/8 16:12
     * @Param： [checkVal]
     * @return： boolean
     **/
    private boolean checkSendCount(String checkVal) {
        boolean returnVal = false;
        boolean initVal = false;
        String baseCurrentTime = String.valueOf(System.currentTimeMillis());
        String checkSendCountVal = EnumSingleton.getVerificationCodeInstance().VerificationCount.get(checkVal);
        // 判断是否发送过验证码
        if (null != checkSendCountVal && !("".equals(checkSendCountVal))) {
            String[] verificationCodeInfo = checkSendCountVal.split("-");
            String createCurrentTime = verificationCodeInfo[0];
            int verificationCount = Integer.parseInt(verificationCodeInfo[1]);
            // 判断是否超过验证码有效期，如果超过则重新计数。
            if (checkTimeOut(createCurrentTime)) {
                initVal = true;
            } else {
                // 判断在一次过期时间内是否超出发送邮件次数，如果超出则不让发送邮件，反之可以发送，并多记一次。
                if (maxCount > verificationCount) {
                    verificationCount++;
                    EnumSingleton.getVerificationCodeInstance().VerificationCount.put(checkVal, createCurrentTime + "-" + verificationCount);
                    returnVal = true;
                }
            }
        } else {
            initVal = true;
        }
        if (initVal) {
            checkSendCountVal = baseCurrentTime + "-1";
            EnumSingleton.getVerificationCodeInstance().VerificationCount.put(checkVal, checkSendCountVal);
            returnVal = true;
        }
        return returnVal;
    }
}
