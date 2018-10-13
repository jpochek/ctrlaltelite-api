package com.charter.interpretme.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {
    public static String encrypt(String clearText){
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(
                clearText.getBytes(StandardCharsets.UTF_8) );
        return encodedString;
    }

    public static String decrypt(String encryptedString) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(encryptedString);
        return new String(decodedByteArray);
    }

}
