package com.Versetti.AuthChallenge.infraestructure.utilities;

public class Base64Utils {
    public static String encode(String base64Text) {
        byte[] encodedBytes = java.util.Base64.getEncoder().encode(base64Text.getBytes());
        return new String(encodedBytes);
    }
}
