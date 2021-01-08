package com.bootdo.clouddopay.config;
import org.springframework.stereotype.Component;

/**
 *  支付宝第三方支付参数配置
 *  @author lixiaole
 */
@Component
public class AlipayConfig {

    /**
     * 商户APPID
     */
    public static  String APP_ID = "2017052601196244";

    /**
     * 私钥
     */
    public static  String PRIVARY_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCB8S2SWSMmyp3qM7G9DwkY7IuioMoxw07llp6dmaZ7gx4DqeN8EDPCyIzTCKKH6R+SL+YQG6EqCX1u5fNBMSRHVUZGT/NcYowCu9ueW/qA9I0G0ivdpvOyeDC3XqmbwRO9OZJekKkLD/tgXi/pk32VL1j0yWrAofmPRridGFv7m/BDpAWZL78opC5iPpJ1eYvohMXgUOp49EFK9xKoE93sAmKyfARyTyzTvdTivPKW8NCtPOeRg1fKcuxljymeQQI4oFYC+fk3Oh46QzjiKuYVKTxarHDtcyljvkqqMiIgt8ble90uywxQES5jOzAh7eT8VVmxPMTsS3m7aw32/EPpAgMBAAECggEAGd5guyzhsZxOucxWyxOONRcKaXLnLeb68vv8UdW/7gDZXdNZhV5YX6O7XVvvt2zyqxC8FWLav9qpzQUcW1MkLMtONTs/cJnx062AeFNJWJjW5o+6yeKty07gK/E9hNN4UYh/mjHkZV5mJfY6RZ7mNmf5pqnehcD5RMvIfwXVcg56JhnDOYbv40bDcoL7w2PiDpSSj5dhqmv2s1M1gke0f2WiBFxgVOhwnvGkcoOom5EV9EMH7RQRDC+4PnqO9nv1ZCEMQsgBrNtWgBrxl52rZI4uoo/cPF0sWrZyendGsk+p4T6jAudKYICskN2WP9hz2Lt1+u7TGUkoVYYe26hojQKBgQD5G6Ps5mMEj3hZ+4cL8YrzFs3NHYHqYUkNudMCYKNJhNH8YyUZcqn5BVN/32+21BAiOtkl05yZDuiHok/0TkJ3sfEm+w1cCX4YUjxFvmz3LMbl0FLyFjrMqdbdK1iESKARYjVUqrGvoRIzF6TfwQGfGyVUyrHz4RZd8CNq2V5GnwKBgQCFiYUzkvN5FBESUiUvE0DhVKfIMeLkMdnWyp0XaWFqr6DrigTyJ6NeARyfsFIi6onYvmIu88McNC1mRQbJxFS4ekZl4EOlOeE84HHQTPsczDdYCWNje+7725QDdp0o8rY06ohboZ+RE6INsIQ4h7hhZ6CNp12CnXDD1nSA9ZCQdwKBgQCbU0DDVmQnU84XTV7Hc7TQHkf0vZFkM4T5qgSIVvl5vfexB6D6XSTewRNviEKAHXpx3X/ItknOph0XdX2lN6b6O1nClIef+8tEHwK1+ia3eJQMd/lAOdwwATaYcY6f7SjhrfrS8BwbBrPyZ6vGWPJJ4wND+586Ef+AU1vCJAdEwQKBgDJfiAHGg1W/PhldJ220o5rcplYci2xtabFeyReVxwvJqLhxXiBC15uUa8nJZ5KISuuaFcuEZKt0+1NC4qamCxdz+K5hhOMFDHQ8bnKXN3rLhm0NTZKvo50aXaFY0zwpIpafikSWmWzOrSjg11/DwsfroWMOKXYmEe2uLlCfUaNNAoGAU74/YNTt/EL0Bp1oDXGodXUWz9RixzCDKB3+3IhbsWkWuFsUVH1TCT3FPiJdo900KBvJmnnJvByX9e6vnF0d7ZPyESoJjskd315iDQlxm/oTmktgCHBmo3GL5nUuCjDUQMXFM78mT7+vVuvQfFPaOveLfGAe3Fj8aKRWbNxFI5Y=";

    /**
     * 应用公钥
     */
    public static  String PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgfEtklkjJsqd6jOxvQ8JGOyLoqDKMcNO5ZaenZmme4MeA6njfBAzwsiM0wiih+kfki/mEBuhKgl9buXzQTEkR1VGRk/zXGKMArvbnlv6gPSNBtIr3abzsngwt16pm8ETvTmSXpCpCw/7YF4v6ZN9lS9Y9MlqwKH5j0a4nRhb+5vwQ6QFmS+/KKQuYj6SdXmL6ITF4FDqePRBSvcSqBPd7AJisnwEck8s073U4rzylvDQrTznkYNXynLsZY8pnkECOKBWAvn5NzoeOkM44irmFSk8Wqxw7XMpY75KqjIiILfG5XvdLssMUBEuYzswIe3k/FVZsTzE7Et5u2sN9vxD6QIDAQAB";

    /**
     * 支付宝公钥
     */
    public static  String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvS/JAFVwYes0nnjfucalsUacjCh7X0JMjdFfjwoee6smmymOoT78oRpFzHS46VGEJvbV4xwnk113/fk9xQHP6j4Re7ImmRKfnTktUyb5Ev1z7lNnzAT5pHNNmk33voBq6FQaAt2rjT6g7OkLz0PIGgG5OsY2epnHPSzGSk79t6x7mVfgVE45cN7ewUhz7YVNnH2o1QG9fIxNoXSILpU3Gf+YRSZ3fa2ev7Y0ApthyxdAirMOCm4Xlkv+KImYeI8HHv63TeWssNArH1XEFydCTLY7XZbVMo4jgWZXbHReDA6G+DYu0rtcKaLp/g0MfuP5tFOa161DuOu3w8BVeit2dQIDAQAB";

    /**
     * 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static  String NOTIFY_URL = "http://petch.free.idcfengye.com/alipay/orderNotify";

    /**
     * 跳转页面，买家支付成功后跳转的页面
     */
    public static  String RETURN_URL = "http://petch.free.idcfengye.com/returnUrl";

    /**
     *  RSA2
     */
    public static  String SIGN_TYPE = "RSA2";

    /**
     *  编码
     */
    public static  String CHARSET = "UTF-8";

    /**
     *  返回格式
     */
    public static  String FORMAT = "json";

    /**
     * 日志记录目录
     */
    public static  String LOG_PATH = "/log";

    /**
     * 支付网关
     */
    public static  String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    /**
     * H5 销售产品码 必填
     */
    public static String PRODUCT_CODE = "QUICK_WAP_PAY";
}
