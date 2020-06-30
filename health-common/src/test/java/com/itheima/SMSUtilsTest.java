package com.LIGY;

import com.aliyuncs.exceptions.ClientException;
import com.LIGY.utils.SMSUtils;


public class SMSUtilsTest {
    private void test01() throws ClientException {
        SMSUtils.sendShortMessage("SMS_178460500","15827173307","1234");
    }
}
