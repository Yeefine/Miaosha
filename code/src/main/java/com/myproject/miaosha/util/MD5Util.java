package com.myproject.miaosha.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPass2FormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPass2DBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPass2DBPass(String input, String saltDB) {
        String formPass = inputPass2FormPass(input);
        String dbPass = formPass2DBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
//        System.out.println(inputPass2FormPass("123456"));
//        System.out.println(formPass2DBPass(inputPass2FormPass("123456"), "1a2b3c4d"));
        System.out.println(inputPass2DBPass("123456", "1a2b3c4d"));
    }
}
