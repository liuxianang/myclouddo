package com.bootdo.clouddopay.config;

/**
 * 微信支付配置文件
 * @author Administrator
 *
 */
public class WeixinPayConfig {

    public static final String appid="wx5e97e532d120a6bf"; // 公众账号ID

    public static final String mch_id="1500625891"; // 商户号

    public static final String device_info="WEB"; // 设备号

    public static final String url="https://api.mch.weixin.qq.com/pay/unifiedorder"; // 支付请求地址

    public static final String notify_url="http://pay2.java1234.com/notifyUrl"; // 公众账号ID

    public static final String key="jrBXpyMVPNY0FCFI42EBShLom7KMaRBe"; // 商户的key【API密钥】
}
