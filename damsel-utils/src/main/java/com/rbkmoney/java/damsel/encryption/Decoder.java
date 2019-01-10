package com.rbkmoney.java.damsel.encryption;

import javax.xml.bind.DatatypeConverter;

public class Decoder {

    public static String base64DecodeAndGetHexString(String str) {
        byte[] bytes = java.util.Base64.getDecoder().decode(str.getBytes());
        return DatatypeConverter.printHexBinary(bytes);
    }

}
