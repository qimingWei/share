package com.share.api.util;

public class EnumSingleton {

    private enum MyEnumSingleton {
        singletonFactory;

        private VerificationCodeUtil instance;
        MyEnumSingleton() {
            instance = new VerificationCodeUtil();
        }
        public VerificationCodeUtil getInstance() {
            return instance;
        }
    }

    public static VerificationCodeUtil getVerificationCodeInstance(){
        return MyEnumSingleton.singletonFactory.getInstance();
    }

}
