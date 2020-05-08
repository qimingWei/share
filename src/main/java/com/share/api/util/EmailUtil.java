package com.share.api.util;

import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @Author：weiqiming
 * @Description：邮箱功能 工具类
 * @Date：2020/5/7 14:12
 **/
@Component
@RequiredArgsConstructor
public class EmailUtil {

    private final JavaMailSender mailSender;

    private final FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${mail.fromMail.addr}")
    private String fromMail;

    public void sendMail(String to, String subject, String templateName, Map<String, Object> dataMap) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // 设置模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            String templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, dataMap);

            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(templateHtml, true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发送邮件异常", e);
        }
    }

}
