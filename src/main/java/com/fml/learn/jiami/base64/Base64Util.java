package com.fml.learn.jiami.base64;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * base64
 */
public class Base64Util {
    private static String src = "zmy dsb";

    public static void main(String[] args) {
        // jdkBase64();
        commonsCodecBase64();
    }

    public static void jdkBase64(){
        try {

        // 编码
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(src.getBytes());
        System.out.println("encode:  :" + encode);
        // 解码
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(encode);
            System.out.println("decode:  :" + new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void commonsCodecBase64(){
        // 编码
        byte[] bytes = Base64.encodeBase64(src.getBytes());
        System.out.println("encode:  :" + new String(bytes));
        // 解码
        byte[] decode = Base64.decodeBase64(bytes);
        System.out.println("decode:  :" + new String(decode));

    }
    public static void bouncyCastleBase64(){
    }

}
