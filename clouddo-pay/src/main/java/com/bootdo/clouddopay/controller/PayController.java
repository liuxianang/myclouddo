package com.bootdo.clouddopay.controller;

import com.bootdo.clouddopay.config.WeixinPayConfig;
import com.bootdo.clouddopay.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/pay")
public class PayController{
    @GetMapping("LoadPayQRCode")
    public String LoadPay() throws IOException {
        String orderNo= DateUtil.getCurrentDateStr(); // 生成订单号
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("appid", WeixinPayConfig.appid); // 公众账号ID
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        map.put("device_info", WeixinPayConfig.device_info); // 设备号
        map.put("notify_url", WeixinPayConfig.notify_url); // 异步通知地址
        map.put("trade_type", "NATIVE"); // 交易类型
        map.put("out_trade_no", orderNo); // 商户订单号
        map.put("body", "liuxianang" ); // 商品描述
        map.put("total_fee", (int)(Math.random()*10+1)); // 标价金额
        // map.put("spbill_create_ip", getRemortIP(request)); // 终端IP
        map.put("spbill_create_ip", "127.0.0.1"); // 终端IP
        map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
        map.put("sign", getSign(map)); // 签名
        String xml= XmlUtil.genXml(map);
        System.out.println(xml);
        InputStream in= HttpClientUtil.sendXMLDataByPost(WeixinPayConfig.url, xml).getEntity().getContent(); // 发现xml消息
        String code_url=getElementValue(in,"code_url"); // 获取二维码地址
        System.out.println(">>>>>>>>>>>"+code_url);
        return code_url;
    }
    @GetMapping("OrderQuery")
    public void OrderQuery() throws IOException {
        String url="https://api.mch.weixin.qq.com/pay/orderquery";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("appid", WeixinPayConfig.appid); // 公众账号ID
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        map.put("transaction_id", "4200000087201804105653326283"); // 微信订单号
        // map.put("out_trade_no", "20180405055656553"); // 商户订单号
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
        map.put("sign", getSign(map)); // 签名
        String xml=XmlUtil.genXml(map);
        System.out.println(xml);
        InputStream in=HttpClientUtil.sendXMLDataByPost(url, xml).getEntity().getContent(); // 发现xml消息
        getElementValue(in);

    }
    @GetMapping("Refund")
    public void Refund() throws IOException {
        String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("appid", WeixinPayConfig.appid); // 公众账号ID
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        map.put("transaction_id", "4200000094201804192059258077"); // 微信订单号
        //map.put("out_trade_no", "20180419105343760"); // 商户订单号
        map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
        map.put("out_refund_no", DateUtil.getCurrentDateStr()); // 商户退款单号
        map.put("total_fee", 100); // 订单金额
        map.put("refund_fee", 10); // 退款金额
        map.put("sign", getSign(map)); // 签名
        String xml=XmlUtil.genXml(map);
        System.out.println(xml);
        InputStream in=HttpClientUtil.sendXMLDataByHttpsPost(url, xml).getEntity().getContent(); // 发现xml消息
        getElementValue(in);

    }
    @GetMapping("RefundQuery")
    public void RefundQuery() throws IOException {
        String url="https://api.mch.weixin.qq.com/pay/refundquery";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("appid", WeixinPayConfig.appid); // 公众账号ID
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        // map.put("transaction_id", "4200000094201804192059258077"); // 微信订单号
        // map.put("out_trade_no", "20180419105343760"); // 商户订单号
        // map.put("out_refund_no", "20180427112536831"); // 商户退款单号
        map.put("refund_id", "50000506552018042704327042706"); // 微信退款单号
        map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
        // map.put("offset", 1); // 偏移量
        map.put("sign", getSign(map)); // 签名
        String xml=XmlUtil.genXml(map);
        System.out.println(xml);
        InputStream in=HttpClientUtil.sendXMLDataByPost(url, xml).getEntity().getContent(); // 发现xml消息
        getElementValue(in);

    }
    @GetMapping("CloseOrder")
    public void CloseOrder() throws IOException {
        String url="https://api.mch.weixin.qq.com/pay/closeorder";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("appid", WeixinPayConfig.appid); // 公众账号ID
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        map.put("out_trade_no", "20180404022005421"); // 商户订单号
        map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
        map.put("sign", getSign(map)); // 签名
        String xml=XmlUtil.genXml(map);
        System.out.println(xml);
        InputStream in=HttpClientUtil.sendXMLDataByPost(url, xml).getEntity().getContent(); // 发现xml消息
        getElementValue(in);
    }


    private String getSign(Map<String,Object> map) {
        StringBuffer sb = new StringBuffer();
        String[] keyArr = (String[]) map.keySet().toArray(new String[map.keySet().size()]);//获取map中的key转为array
        Arrays.sort(keyArr);//对array排序
        for (int i = 0, size = keyArr.length; i < size; ++i) {
            if ("sign".equals(keyArr[i])) {
                continue;
            }
            sb.append(keyArr[i] + "=" + map.get(keyArr[i]) + "&");
        }
        sb.append("key=" + WeixinPayConfig.key);
        String sign = Md5Util.string2MD5(sb.toString());
        return sign;
    }
    private String getElementValue(InputStream in,String key){
        SAXReader reader = new SAXReader();
        Document document=null;
        try {
            document = reader.read(in);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element child : childElements) {
            System.out.println(child.getName()+":"+child.getStringValue());
            if(key.equals(child.getName())){
                return child.getStringValue();
            }
        }
        return null;
    }
    private static void getElementValue(InputStream in){
        SAXReader reader = new SAXReader();
        Document document=null;
        try {
            document = reader.read(in);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element child : childElements) {
            System.out.println(child.getName()+":"+child.getStringValue());
        }
    }
}
