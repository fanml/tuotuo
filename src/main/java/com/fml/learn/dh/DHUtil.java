package com.fml.learn.dh;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 * java实现非对称加密算法-DH
 *  由于JDK8 update 161之后，DH的密钥长度至少为512位，但DES算法密钥不能达到这样的长度，长度不一致所以导致报错。
 *  -Djdk.crypto.KeyAgreement.legacyKDF=true
 */
public class DHUtil {
    public static PublicKey receiverPublicKey;
    public static KeyPair receiverKeyPar;
    public static KeyPair sendKeyPair;

    /**
     * 初始化发送方密钥
     *
     * @return
     */
    public static byte[] initSendKey() {
        byte[] senderPublicKeyEnc = null;
        try {
            KeyPairGenerator sendKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            sendKeyPairGenerator.initialize(512);
            sendKeyPair = sendKeyPairGenerator.generateKeyPair();
            // 发送方公钥
            senderPublicKeyEnc = sendKeyPair.getPublic().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return senderPublicKeyEnc;
    }

    /**
     * 初始化接收方密钥
     *
     * @return
     */
    public static byte[] initReceiverKey(byte[] senderPublicKeyEnc) {
        byte[] receiverPublicKeyEnc = null;
        try {
            KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
            receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
            // 获得发送方密钥参数
            DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
            KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            receiverKeyPairGenerator.initialize(dhParameterSpec);
            receiverKeyPar = receiverKeyPairGenerator.generateKeyPair();
            receiverPublicKeyEnc = receiverKeyPar.getPublic().getEncoded();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiverPublicKeyEnc;
    }

    /**
     * 接收方密钥构建
     */
    public static SecretKey receiverKeyBuild() {
        SecretKey secretKey = null;
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(receiverKeyPar.getPrivate());
            keyAgreement.doPhase(receiverPublicKey, true);
            secretKey = keyAgreement.generateSecret("DES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * 发送方密钥构建
     */
    public static SecretKey sendKeyBuild(byte[] receiverPublicKeyEnc) {
        SecretKey secretKey = null;
        try {
            KeyFactory sendKeyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
            PublicKey senderPublicKey = sendKeyFactory.generatePublic(x509EncodedKeySpec);
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(sendKeyPair.getPrivate());
            keyAgreement.doPhase(senderPublicKey, true);
            secretKey = keyAgreement.generateSecret("DES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * 加密
     *
     * @param sendSecretKey
     * @param str
     * @return
     */
    public static byte[] dhIn(SecretKey sendSecretKey, String str) {
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, sendSecretKey);
            bytes = cipher.doFinal(str.getBytes());
            System.out.println("jdk dh encrypt: "+ Hex.encodeHexString(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 解密
     *
     * @param receiverSecretKey
     * @param bytes
     * @return
     */
    public static String dhOut(SecretKey receiverSecretKey, byte[] bytes) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, receiverSecretKey);
            byte[] bytes1 = cipher.doFinal(bytes);
            result = new String(bytes1);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
