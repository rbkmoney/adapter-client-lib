package com.rbkmoney.java.damsel.encryption;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.xml.bind.DatatypeConverter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Decoder {

    public static String base64DecodeAndGetHexString(String str) {
        byte[] bytes = java.util.Base64.getDecoder().decode(str.getBytes());
        return DatatypeConverter.printHexBinary(bytes);
    }

}
