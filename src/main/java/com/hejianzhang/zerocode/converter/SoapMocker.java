package com.hejianzhang.zerocode.converter;

import com.hejianzhang.zerocode.core.utils.SmartUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoapMocker {

    public Object soapResponseXml(String nothing){

        try {
//            final String rawBody = SmartUtils.readJsonAsString("soap_response/mock_soap_response.xml");
            final String rawBody = SmartUtils.readJsonAsString(null,null);
            Map<String, String> singleKeyValueMap = new HashMap<>();
            singleKeyValueMap.put("rawBody", rawBody);

            return singleKeyValueMap;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("something wrong happened here" + e);
        }
    }
}
