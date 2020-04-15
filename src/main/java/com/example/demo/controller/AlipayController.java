package com.example.demo.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAppMiniTemplatemessageSendRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayOpenAppMiniTemplatemessageSendResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.example.demo.pojo.UserInfo;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zrf on 2020/4/10.
 **/
@Slf4j
@RestController
@RequestMapping("/userinfo")
public class AlipayController {

    private final static String PUBLIC_ALIPAY_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiQTW8kEYHdAbj/jk/Z2PzqoQ45/1GOoVCdPeC08kknITssC7rWuwNfAEAgRYl/pLkV9hdqZMmjER7f6iBh1u4opx+TXhBRkPtximuRWr+4xvEXW5kVEANKeNmmXRjNJne3eMmA7EFhIYudbhLrXHKJMzo5gYFqgtOvctKwJiZ8QirVgj5JFwnzfkHfWezUHqfWeAOZNY2ld0pjQJABZ7o+jHNftInr/Uka+dmrDngTt3rimB3sjLqF2pyMCC369gQvhz3mja1X2+F+s6NcKWAPjn3PYZQwTv61kIXH2b45iu9YWaFbqY0v3HMF6diIegwCGPz5iSEzltH5b84JPcqQIDAQAB";
    private final static String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDADYbtzB/V8cmOm4zjmEBZYaaEYaqHJmQ7YA9dqMMPqTWhV5HxeJS7aHLpm46eDWq5WXD5zK4PJhG91yFq9lG0VH1l399UEFuc63T+MyVaZoT1Oe4AevOqmYwls5hfbNf40UHh+OJSmqNQS9aoAVLljBrwhwM5Cu6KJaBcWyeq4LHlPMogdDWM66r6cnVqWUrygnGsYHqfrhqNUSvAvO0s6FiIpQ7Wrtioz3ulo3kuuMD3Dzrj+y3wkXImvWgPx4/3qrJgO0a8PGMsGSNY2R2wJdvJ9fQOWgAP+I5q8cVHoncTFLJKhTH03XB6iTQ3LPXFffVWpihi34CVZvzDGPBDAgMBAAECggEAdW3P+sa9EIvuugvScbc+YWib8IXnRwvqbYOm4ZW273R/Hk/1JLid+Jk86in7YhjCEOLneVX1d73HS1SnUkeaXbDrm8bvPu9hlOlDd8q+JDBbS7ZIGHMVWlLYppTRrkdXJwSMTCdp+ygig45big6FKWYpcria993/TOkEzHPQJWJ9vM0ANh/4f6Uv80cnX4GjmI/DXOdVtKBZrlPyuZRjZ7pLArKSWPACexuVeUxai320sAt3CZ7k24f6P5N+p47TLEIj0FtgfcwnR64a2gT18AXp8jlBTKLxNE2S0j4YKjOjR9nvjS7i5KHYI3bG8ZVxaeX0UUnAAe0EiV3SX/npAQKBgQD2RJmQkjl8FtZMj/0uOaXDAoSX0P06wmS0gT9fRryyp5i/4RqzleZIhHPpaBDXTCrq7WweK5Dj8z7ww9lS4PpjCJ/uSxml/PYTh6m5PYaN2jAnTaPc7pxiuzcvGBZFN809QYZhF83WbxFiB+soaPkmEZg75VOrUYx2W51G/X6AEwKBgQDHpHQFJmBzchyg9JvZAotXHYHjlrt/D0hjJHaTthwM9Qw37R0oU7q990r1GnMtJ8Cv+f9ECoIDSqs+O7SdXbwWh1xvotvpQw1yUfauYt/6XTfTgd74+9X0rfJW5FsnFLcJf80H0Alip8DvflP5qOqoGSV3q+eC3tlCZ1x35Oe1EQKBgQC1bsbgXwpg5IZPc8moc6ou6Lz9CGh4EN2Wqp4guIGu8ZC01MgSRL6a8JJhBF1b8O6xk+eStrARcJUPVorW548wxRrmuYeU5a/5pXoiUjS+b2AIpMezrkui5aD7XjiSGFFp7sts3te0QrHA0mqqo+Pi6xWIEyU4U2Uzkb1mnr3C5wKBgCtZ65OetLz833iYBSuHYOiG0Mix138Mu4RDy7fhiI/Lul5+j6FNJTTQ+SbmGnKIuk+8MpD80ySR10OJRXwSeaBOvmTDcYticw4K/Ez7zXUZPuEfx2I7lUcvansx1RVUazaV2NjEdPVGRh/7R5Lj+iiMZVVqL7h8WaMQvMSQITxxAoGBAId1pFQmyBfnWPrn4rJOUvMJkeBvCRgnonusqidpXKcZKl7eiWmjugUi1M6UxCACCwlMP4Zy8eiOX1n4ML1HkmRLo/3g5orl+kDmdR2ezhh74ZymTgszzo/68sRRtbrEekSJTQoc93SdzMbrUk0oiNQaKJcOcNWCxV2mJ30Wv8om";


    public static void main(String[] args) throws AlipayApiException {
        AlipayClient alipayClient =new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2021001151681088",PRIVATE_KEY,"json","UTF-8",PUBLIC_ALIPAY_KEY,"RSA2");

        AlipayOpenAppMiniTemplatemessageSendRequest request =new AlipayOpenAppMiniTemplatemessageSendRequest();




        request.setBizContent("{"+
                "\"to_user_id\":\"2088602305833448\","+
                "\"form_id\":\"MjA4ODYwMjMwNTgzMzQ0OF8xNTg2NTA3NDU0NTc1XzA2MQ==\","+
                "\"user_template_id\":\"MzY0MDc2NDk0MGQ5YmJhZjc1NDk0OTMyZThhNzMzODk=\","+
                "\"page\":\"page/component/index\","+
                "\"data\":\"{\\\"keyword1\\\":{\\\"value\\\":\\\"12:00\\\"},\\\"keyword2\\\":{\\\"value\\\":\\\"20180808\\\"},\\\"keyword3\\\":{\\\"value\\\":\\\"支付宝\\\"}}\""+
                "}");

        AlipayOpenAppMiniTemplatemessageSendResponse response = alipayClient.execute(request);

        if(response.isSuccess()){
            System.out.println("调用成功");
        }else{
            System.out.println("调用失败");
        }

//        UserInfo info;
//        AlipayClient alipayClients = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
//                "2021001151681088", PRIVATE_KEY, "json",
//                "UTF-8", PUBLIC_ALIPAY_KEY, "RSA2");
//        AlipayUserInfoShareRequest req = new AlipayUserInfoShareRequest();
//        AlipayUserInfoShareResponse res = null;
//        try {
//            res = alipayClients.execute(req, "authusrB2ae080b0ceaa481692b47f6644a8aX44");
//        } catch (AlipayApiException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        if (res.isSuccess()) {
//            info = new UserInfo();
//            info.setUserId(res.getUserId());
//            info.setAvatar(res.getAvatar());
//            info.setNickName(res.getNickName());
//            System.out.println(info);
//        } else {
//            info = new UserInfo();
//            System.out.println(info);
//        }
    }


    @Autowired
    private AlipayClient alipayClient;


    @GetMapping("/alipayUserInfo")
    public Object alipayUserInfo(String authCode) throws Exception {
        if (isBlank(authCode)) {
            log.warn("授权编码不能为空!");
            throw new Exception("授权编码不能为空");
        }
        UserInfo info;
        try {
            // token and userId
            AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse = this.getAccessToken(authCode);
            if (!alipaySystemOauthTokenResponse.isSuccess()) {
                log.warn("换取 AuthToken 失败！错误编码：{}, 错误信息：{}", alipaySystemOauthTokenResponse.getCode(), alipaySystemOauthTokenResponse.getMsg() );
                return alipaySystemOauthTokenResponse;
            }
            String accessToken = alipaySystemOauthTokenResponse.getAccessToken();
            return alipaySystemOauthTokenResponse;

//            log.info("通过AuthCode:{} 换取AuthToken:{} 成功！", authCode, accessToken );
//
//            // get user by accessToken
//            AlipayUserInfoShareResponse alipayUserInfoShareResponse = this.getUserInfoFromAlipay(accessToken);
//            return alipayUserInfoShareResponse;
        }catch (AlipayApiException e) {
            if(e.getCause() instanceof java.security.spec.InvalidKeySpecException){
                log.error("商户私钥格式不正确，请确application.properties文件中是否配置正确");
            }
            return "商户私钥格式不正确，请确application.properties文件中是否配置正确";
        }
    }

    private AlipaySystemOauthTokenResponse getAccessToken(String authCode) throws AlipayApiException {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);
        request.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");
        return alipayClient.execute(request);
    }

    private AlipayUserInfoShareResponse getUserInfoFromAlipay(String accessToken) throws AlipayApiException {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        return alipayClient.execute(request, accessToken);
    }

    public static boolean isBlank(String str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
}
