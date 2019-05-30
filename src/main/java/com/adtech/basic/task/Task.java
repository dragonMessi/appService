package com.adtech.basic.task;

import com.adtech.basic.util.HttpClientUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableAsync
public class Task {

//    private static final String urlOne = "http://172.20.195.209:80/ios/restFull/";
//    private static final String urlTwo = "http://172.20.195.209:80/ios/restFulVersionTwo/";

    private static final String urlOne = "http://127.0.0.1:80/ios/restFull/";
    private static final String urlTwo = "http://127.0.0.1:80/ios/restFulVersionTwo/";
    //vip执行第三方订单 vipThirdOrderPerform
    @Scheduled(cron = "0 0/10 * * * ? ")//10分钟都执行
    @Async
    public void vipThirdOrderPerform() {

        try {
            String result = HttpClientUtil.doget(urlOne + "vipThirdOrderPerform.do");

            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip执行第三方订单 vipThirdOrderPerform");
        } catch (Exception e) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip执行第三方订单 vipThirdOrderPerform报错");
        }

    }


    //vip执行第三方订单状态查询 vipThirdOrderStatus
    @Scheduled(cron = "0 0/30 * * * ? ")//10分钟都执行
    @Async
    public void vipThirdOrderStatus() {
        try {
            String result = HttpClientUtil.doget(urlOne + "vipThirdOrderStatus.do");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip执行第三方订单状态查询vipThirdOrderStatus");
        } catch (Exception e) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip执行第三方订单状态查询vipThirdOrderStatus报错");
        }
    }


    //vip订单每天自动添加 autoVipThirdOrder
    @Scheduled(cron = "0 0/30 * * * ? ")//10分钟都执行
    @Async
    public void autoVipThirdOrder() {
        try {
            String result = HttpClientUtil.doget(urlOne + "autoVipThirdOrder.do");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip订单每天自动添加 autoVipThirdOrder");

        } catch (Exception e) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip订单每天自动添加 autoVipThirdOrder报错");
        }

    }


    //自动添加VIP第三方订单第二版
    @Scheduled(cron = "0 0/30 * * * ? ")//5分钟都执行
    @Async
    public void autoVipThirdOrderTwo() {
        try {
            String result = HttpClientUtil.doget(urlTwo + "autoVipThirdOrderTwo.do");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "自动添加VIP第三方订单第二版autoVipThirdOrderTwo");

        } catch (Exception e) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "自动添加VIP第三方订单第二版autoVipThirdOrderTwo报错");
        }
    }

    //vip第三方订单添状态查询第二版
    @Scheduled(cron = "0 0/20 * * * ? ")//5分钟都执行
    @Async
    public void vipThirdOrderStatusTwo() {
        try {
            String result = HttpClientUtil.doget(urlTwo + "vipThirdOrderStatusTwo.do");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip第三方订单添状态查询第二版vipThirdOrderStatusTwo");
        } catch (Exception e) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip第三方订单添状态查询第二版vipThirdOrderStatusTwo报错");
        }
    }

    //vip第三方订单执行第二版
    @Scheduled(cron = "0 0/60 * * * ? ")//5分钟都执行
    @Async
    public void vipThirdOrderPerformTwo() {
        try {    String result = HttpClientUtil.doget(urlTwo + "vipThirdOrderPerformTwo.do");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip第三方订单执行第二版vipThirdOrderPerformTwo");

        } catch (Exception e) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "vip第三方订单执行第二版vipThirdOrderPerformTwo报错");
        }
    }

}