<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>验证码邮件</title>
</head>
<body>
<h4>    您好！</h4>
<p>         这是一条验证用户邮箱的注册验证码信息，完成密码修改或绑定邮箱功能（请千万不要将此验证码泄露给他人）:</p>
<h3>　　      验证码：${verificationCode ! ''}</h3>
<p>(此邮件为系统自动发送，请勿回复)</p>
<p style="width: 100%;text-align: right">VR分享系统</p>
<p style="width: 100%;text-align: right">    ${date ! ''}</p>
</body>
</html>