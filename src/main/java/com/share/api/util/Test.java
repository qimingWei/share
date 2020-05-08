package com.share.api.util;

public class Test {

    public static void main(String[] args) {
        String baseCurrentTime = String.valueOf(System.currentTimeMillis());
        System.out.println(baseCurrentTime);
        String val = String.valueOf((System.currentTimeMillis() - 1588843725329L)/60000);
        String verificationCode = baseCurrentTime.substring(0x8);
        System.out.println(val);
    }
}
