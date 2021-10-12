package com.fml.learn.jiami.dh;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKey;

public class SendDH {
    public static void main(String[] args) {
        // 初始化发送方密钥
        byte[] senderPublicKeyEnc = DHUtil.initSendKey();

        System.out.println("发送方密钥数组" + Hex.encodeHexString(senderPublicKeyEnc));
         // 测试Hex转换
        String s = Hex.encodeHexString(senderPublicKeyEnc);
        byte[] bytes = null;
        try {
            bytes = Hex.decodeHex(s);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        // 初始化接收方密钥
        byte[] receiverPublicKeyEnc = DHUtil.initReceiverKey(bytes);
        System.out.println("接收方密钥数组" + Hex.encodeHexString(receiverPublicKeyEnc));
        // 发送方密钥构建
        SecretKey sendSecretKey = DHUtil.sendKeyBuild(receiverPublicKeyEnc);
        System.out.println("发送方密钥"+sendSecretKey.toString());
        // 接收方密钥构建
        SecretKey receiverSecretKey = DHUtil.receiverKeyBuild();
        System.out.println("接收方密钥"+receiverSecretKey.toString());
        // 加密
        byte[] tests = DHUtil.dhIn(sendSecretKey, "test");
        // 解密
        DHUtil.dhOut(receiverSecretKey,tests);


    }
}
