package com.fml.learn.jiami.md;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * java实现消息摘要算法加密
 */
public class MDUtil {
    static String src = "i love code";

    public static void main(String[] args) {
        jdkMd2();
        jdkMd5();
        jdkSHA1();
        jdkHmacMD5();
    }

    /**
     * hash算法 方法不可逆
     *     用户注册功能
     * （1）用户注册（2）应用服务器对密码进行消息摘要（3）数据库信息持久化（4）返回注册结果
     *
     *     用户登录
     * （1）用户登录（2）应用服务器对密码进行消息摘要（3）数据库通过用户名及摘要查询（4）返回登录结果
     */
    private static void jdkMd2() {
        try {
            MessageDigest md = MessageDigest.getInstance("md2");
            byte[] md2bytes = md.digest(src.getBytes());
            String encode = Hex.encodeHexString(md2bytes);
//			System.out.println("JDK MD2 encode " + new String(md2bytes));	这样不行，要转为16进制字符串
            System.out.println("JDK MD2 encode " + encode);
//			byte[] md2bytes_decode = md.digest(encode.getBytes());
//			System.out.println("JDK MD2 decode " + new String(md2bytes_decode));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void jdkMd5() {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5bytes = md.digest(src.getBytes());
            String encode = Hex.encodeHexString(md5bytes);
            System.out.println("JDK MD5 encode " + encode);
//			byte[] md5bytes_decode = md.digest(encode.getBytes());
//			System.out.println("JDK MD5 decode " + new String(md5bytes_decode));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 安全散列算法
     * 固定长度摘要信息
     *
     * 应用
     *
     * 浏览器证书
     * 消息摘要算法-SHA 应用（SHA算法消息传递）
     * 发送方：1.公布消息摘要算法 2.对待发布消息进行摘要处理 3.发布摘要消息 4.发送消息
     * 接收方：消息鉴别
     * 消息鉴别是指在接受方将原始信息进行摘要，然后与接受到的摘要信息进行对比
     *
     * 应用例子
     * - 加入约定key
     * - 增加时间戳
     * - 排序
     * http://**?msg=12Hsad74mj&timestamp=1309488734
     * msg:原始消息+key+时间戳
     */
    private static void jdkSHA1() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("sha");
            md.update(src.getBytes());
            System.out.println("jdk sha-1:" + Hex.encodeHexString(md.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 含有密钥的散列函数算法
     * 融合MD、SHA
     * 应用如SecureCRT
     * 发送方：1.公布消息摘要算法 2.构建密钥 3.发送密钥 4.对待发消息进行摘要处理 5.发送消息摘要 6.发送消息
     * 接受方：消息鉴别
     */
    private static void jdkHmacMD5() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");//初始化KeyGenerator
            SecretKey secretKey = keyGenerator.generateKey();	//产生密钥
            byte[] key = secretKey.getEncoded();		//获得密钥

            SecretKey restoreSecretKey = new SecretKeySpec(key,"HmacMD5");	//还原密钥
            Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());		//实例化mac
            mac.init(restoreSecretKey);					//初始化mac
            byte[] hmacMD5Bytes = mac.doFinal(src.getBytes());	//执行摘要
            System.out.println("jdk HmacMD5: " + Hex.encodeHexString(hmacMD5Bytes));


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 消息摘要算法-其他
     *
     * RipeMD
     *
     * Tiger
     *
     * Whirlpool
     *
     * GOST3411
     *
     * Bouncy Castle实现
     */


}
