package test.test;



import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class SignUtil {
    /**

     如果java环境搭建好，注释中内容可以新建一个bat来执行，或者直接粘贴到黑屏执行，即可生成私钥和公钥的证书

     REM # create keystore file
     keytool -genkey -dname "CN=Test Bank, OU=Test, O=Test, L=BeiJing, ST=BeiJing, C=CN" -alias testAlias -keyalg RSA -keysize 2048 -keystore TestPrivate.jks -keypass 12345678 -storepass 12345678 -validity 365

     REM # export cert file
     keytool -export -alias testAlias -file TestPublic.cer  -keystore TestPrivate.jks -storepass 12345678


     **/

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 签名方法
     * @param srcByte 待签名数据(byte)
     * @param keystorePath 密钥库文件路径
     * @param keystorePwd 密钥库密码
     * @param keystoreType 密钥库类型 无设置默认JKS
     * @param algorithm 算法 无设置默认取证书算法
     * @param alias 私钥别名 无设置默认自动查找
     * @return
     */
    public static byte[] sign(byte[] srcByte,String keystorePath,String keystorePwd,String keystoreType,String algorithm,String alias){

        if(srcByte==null){
            System.out.println("待签名数据为空！");
            return null;
        }

        if(keystorePath==null||"".equals(keystorePath)){
            System.out.println("密钥库路径为空！");
            return null;
        }

        if(keystorePwd==null){
            System.out.println("密钥库密码为空！");
            return null;
        }

        KeyStore keyStore = getKeyStore(keystorePath, keystorePwd, keystoreType);

        if(keyStore==null){
            System.out.println("获取密钥库对象失败！");
            return null;
        }

        if(alias==null||"".equals(alias)){
            alias = getAlias(keyStore);
        }

        PrivateKey privateKey = getPrivateKey(keyStore, keystorePwd, alias);
        System.out.println(">>>>>>>>"+privateKey);


        if(privateKey==null){
            System.out.println("获取私钥对象失败！");
            return null;
        }

        X509Certificate x509Certificate = getX509Certificate(keyStore, alias);

        if(algorithm==null||"".equals(algorithm)){
            algorithm = x509Certificate.getSigAlgName();
        }

        return doSign(srcByte, privateKey, algorithm);
    }

    /**
     * 签名方法
     * @param srcData 待签名数据(String)
     * @param keystorePath 密钥库文件路径
     * @param keystorePwd 密钥库密码
     * @param keystoreType 密钥库类型 无设置默认JKS
     * @param algorithm 签名算法 无设置默认取证书算法
     * @return
     */
    public static String sign(String srcData,String keystorePath,String keystorePwd,String keystoreType,String algorithm){

        String signedData = "";

        System.out.println("待签名参数："+srcData);

        if(srcData==null||"".equals(srcData)){
            System.out.println("待签名数据为空！");
            return "";
        }

        byte[] srcByte = srcData.getBytes();//待签名数据String转成byte

        byte[] rsByte = sign(srcByte, keystorePath, keystorePwd, keystoreType, algorithm, null);

        if(rsByte==null){
            System.out.println("签名失败！");
            return "";
        }

        signedData = new BASE64Encoder().encodeBuffer(rsByte);

        System.out.println("签名串："+signedData);

        return signedData;
    }

    /**
     * 验签方法
     * @param srcByte 待签名数据(byte)
     * @param signedByte 签名串(byte)
     * @param certificatePath 证书文件路径
     * @param algorithm 验签算法 无设置默认取证书算法
     * @return
     */
    public static boolean verify(byte[] srcByte,byte[] signedByte,String certificatePath,String algorithm){

        if(srcByte==null){
            System.out.println("传入的原数据参数为空！");
            return false;
        }

        if(signedByte==null){
            System.out.println("传入的签名串参数为空！");
            return false;
        }

        if(certificatePath==null||"".equals(certificatePath)){
            System.out.println("公钥证书路径参数为空！");
            return false;
        }

        X509Certificate x509Certificate = getX509Certificate(certificatePath);

        if(x509Certificate==null){
            System.out.println("获取X509证书对象失败！");
            return false;
        }

        PublicKey publicKey = getPublicKey(x509Certificate);

        if(publicKey==null){
            System.out.println("公钥对象获取失败！");
            return false;
        }

        if(algorithm==null||"".equals(algorithm)){
            algorithm = x509Certificate.getSigAlgName();
        }

        return doVerify(srcByte, signedByte, publicKey, algorithm);
    }

    /**
     * 验签方法
     * @param srcData 待签名数据(String)
     * @param signedData 签名串(String)
     * @param certificatePath 证书文件路径
     * @param algorithm 验签算法 无设置默认取证书算法
     * @return
     */
    public static boolean verify(String srcData,String signedData,String certificatePath,String algorithm){

        boolean result = false;

        System.out.println("验签参数(待签名数据)："+srcData);
        System.out.println("验签参数(签名串)："+signedData);

        if(srcData==null||"".equals(srcData)){
            System.out.println("传入待签名数据为空！");
            return false;
        }

        if(signedData==null||"".equals(signedData)){
            System.out.println("传入签名串为空！");
            return false;
        }

        byte[] srcByte = srcData.getBytes();
        byte[] signedByte = null;
        try {
            signedByte = new BASE64Decoder().decodeBuffer(signedData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = verify(srcByte, signedByte, certificatePath, algorithm);

        System.out.println("验签结果："+result);

        return result;
    }

    /**
     * 创建签名对象进行签名
     * @param srcByte 待签名数据(byte)
     * @param privateKey 私钥
     * @param algorithm 签名算法 无设置默认取证书算法
     * @return
     */
    private static byte[] doSign(byte[] srcByte,PrivateKey privateKey,String algorithm){

        System.out.println("签名算法："+algorithm);

        try {

            Signature signature=Signature.getInstance(algorithm);
            signature.initSign(privateKey);
            signature.update(srcByte);
            return signature.sign();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 创建签名对象进行验签
     * @param srcByte 原待签名数据(byte)
     * @param signedByte 签名串(byte)
     * @param publicKey 公钥
     * @param algorithm 验签算法 无设置默认取证书算法
     * @return
     */
    private static boolean doVerify(byte[] srcByte,byte[] signedByte,PublicKey publicKey,String algorithm){

        System.out.println("验签算法："+algorithm);

        try {

            Signature signature=Signature.getInstance(algorithm);
            signature.initVerify(publicKey);
            signature.update(srcByte);
            return signature.verify(signedByte);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 找到路径下的密钥库文件，获取密钥库对象
     * @param keystorePath 密钥库文件路径
     * @param keystorePwd 密钥库密码
     * @param keystoreType 密钥库类型 无设置默认JKS
     * @return
     */
    private static KeyStore getKeyStore(String keystorePath,String keystorePwd,String keystoreType){

        System.out.println("密钥库文件路径："+keystorePath);

        if(keystoreType==null||"".equals(keystoreType)){
            keystoreType = KeyStore.getDefaultType();
        }

        System.out.println("创建密钥库对象使用的密钥库类型："+keystoreType);

        FileInputStream stream = null;
        KeyStore keyStore = null;
        try {
            stream = new FileInputStream(keystorePath);
            keyStore = KeyStore.getInstance(keystoreType);
            keyStore.load(stream, keystorePwd.toCharArray());

            System.out.println("读取文件密钥库对象成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stream = null;
            }
        }

        return keyStore;
    }

    /**
     * 查找密钥库中私钥别名，找到名称就停止查找
     * @param keyStore 密钥库
     * @return
     */
    private static String getAlias(KeyStore keyStore){
        String alias = null;
        try {
            Enumeration<String> aliases = keyStore.aliases();
            while(aliases.hasMoreElements()){
                String element = aliases.nextElement();
                if(keyStore.isKeyEntry(element)){
                    alias = element;
                    break;
                }
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return alias;
    }

    /**
     * 获取密钥库中的私钥
     * @param keyStore 密钥库对象
     * @param keystorePwd 密钥库密码
     * @param alias 私钥的别名
     * @return
     */
    private static PrivateKey getPrivateKey(KeyStore keyStore,String keystorePwd,String alias){

        System.out.println("私钥别名："+alias);

        try {
            return (PrivateKey)keyStore.getKey(alias, keystorePwd.toCharArray());
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 找到路径下的证书文件，获取证书对象
     * @param certificatePath 证书文件路径
     * @return
     */
    private static X509Certificate getX509Certificate(String certificatePath){

        System.out.println("证书文件路径："+certificatePath);

        FileInputStream stream = null;
        X509Certificate x509Certificate = null;
        try {
            stream = new FileInputStream(certificatePath);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("x.509");//1.6版本只支持x.509标准证书
            x509Certificate = (X509Certificate)certificateFactory.generateCertificate(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } finally{
            if(stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stream = null;
            }
        }

        return x509Certificate;
    }

    /**
     * 获取证书的公钥
     * @param x509Certificate 证书对象
     * @return
     */
    private static PublicKey getPublicKey(X509Certificate x509Certificate){
        return x509Certificate.getPublicKey();
    }

    /**
     * 通过密钥库获取证书对象
     * @param keyStore 密钥库
     * @param alias 私钥别名
     * @return
     */
    private static X509Certificate getX509Certificate(KeyStore keyStore,String alias){
        try {
            return (X509Certificate)keyStore.getCertificate(alias);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 明文加密
     * @param plainText 明文
     * @param key 公钥或私钥
     * @return
     */
    public static byte[] encrypt(byte[] plainText, Key key){

        System.out.println("加密方法encrypt开始！");

        ByteArrayOutputStream out = null;
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);

            int inputLen = plainText.length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(plainText, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(plainText, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();

            return encryptedData;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } finally{
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
            System.out.println("加密方法encrypt结束！");
        }

        return null;
    }

    /**
     * 密文解密
     * @param ciphertext 密文
     * @param key 公钥或私钥
     * @return
     */
    public static byte[] decrypt(byte[] ciphertext, Key key){

        System.out.println("解密方法decrypt开始！");

        ByteArrayOutputStream out = null;
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, key);

            int inputLen = ciphertext.length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(ciphertext, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(ciphertext, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();

            return decryptedData;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } finally{
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
            System.out.println("解密方法decrypt结束！");
        }

        return null;
    }


    public static void main(String[] args) {
        String srcData = "test测试数据";

        //签名验签测试
        String rsData = sign(srcData, "D:\\test1\\TestPrivate.jks", "12345678" , null, null);
        System.out.println(">>>>>>>>>>>>"+rsData);
        boolean result = verify(srcData, rsData, "D:\\test1\\TestPublic.cer", null);


        //加密解密测试
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(1024);
        KeyPair key = keyPairGenerator.genKeyPair();
        PrivateKey privateKey = key.getPrivate();
        PublicKey publicKey = key.getPublic();

        byte[] a = encrypt(srcData.getBytes(), privateKey);

        byte[] b = decrypt(a, publicKey);

        System.out.println(new String(b));

    }

}
