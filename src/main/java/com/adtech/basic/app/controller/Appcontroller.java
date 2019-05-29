package com.adtech.basic.app.controller;

import com.adtech.basic.util.HttpClientUtil;
import com.adtech.basic.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Appcontroller {

    //进入followers页面
    @RequestMapping(value = "/goFollowersHtml", method = RequestMethod.GET)
    public String goFollowersHtml(@RequestParam("package_name") String package_name,
                                  @RequestParam("user_id") String user_id,
                                  @RequestParam("user_name") String user_name) {

        String html = "";
        if (user_id.equals("0")) {
            html = "tourists_followers";
        } else {
            html = "followers";
        }
        return html;
    }


    //userInfo
    @ResponseBody
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public JSONObject userInfo(@RequestParam("package_name") String package_name,
                               @RequestParam("user_name") String user_name,
                               @RequestParam("user_id") String user_id) throws Exception {
        Map<String, String> mapParams = new HashMap<String, String>();
        String jsonParams = "{user_id:" + user_id + "}";
        mapParams.put("token", StringUtil.encryptAES(jsonParams));
        JSONObject JONSData = null;
        try {
            String result = HttpClientUtil.dopost(StringUtil.urlOne + "userInfo.do", mapParams);
            result = StringUtil.decryptAES(result);
            JONSData = JSON.parseObject(result);
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
            JONSData = StringUtil.catchMap(StringUtil.SERVICESERRO, StringUtil.STATUS500);
        }

        return JONSData;
    }


    //serviceList，查询服务列表
    @ResponseBody
    @RequestMapping(value = "/serviceList", method = RequestMethod.GET)
    public JSONObject serviceList(@RequestParam("package_name") String package_name,
                                  @RequestParam("type") String type) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> mapParams = new HashMap<String, String>();
        Map<String, String> mapInitializeParams = new HashMap<String, String>();
        mapInitializeParams.put("type", type);
        mapInitializeParams.put("package_name", package_name);
        String jsonParams = JSON.toJSONString(mapInitializeParams,
                SerializerFeature.WriteMapNullValue).toString();//组装要传送的参数
        mapParams.put("token", StringUtil.encryptAES(jsonParams));
        JSONObject JONSData = null;
        try {
            String result = HttpClientUtil.dopost(StringUtil.urlOne + "serviceList.do", mapParams);

            result = StringUtil.decryptAES(result);
            JONSData = JSON.parseObject(result);
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
            JONSData = StringUtil.catchMap(StringUtil.SERVICESERRO, StringUtil.STATUS500);
        }
        return JONSData;
    }


    //buyService
    // 购买服务
    @ResponseBody
    @RequestMapping(value = "/buyService", method = RequestMethod.GET)
    public JSONObject buyService(@RequestParam("package_name") String package_name,
                                 @RequestParam("good_id") String good_id,
                                 @RequestParam("user_id") String user_id,
                                 @RequestParam(required = false) String shortcode,
                                 @RequestParam(required = false) String src,
                                 @RequestParam(required = false) String mediaId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> mapParams = new HashMap<String, String>();
        Map<String, String> mapInitializeParams = new HashMap<String, String>();
        mapInitializeParams.put("user_id", user_id);
        mapInitializeParams.put("service_id", good_id);
        mapInitializeParams.put("package_name", package_name);
        mapInitializeParams.put("shortcode", shortcode);
        mapInitializeParams.put("src", src);
        mapInitializeParams.put("media_id", mediaId);
        String jsonParams = JSON.toJSONString(mapInitializeParams,
                SerializerFeature.WriteMapNullValue).toString();//组装要传送的参数

        mapParams.put("token", StringUtil.encryptAES(jsonParams));
        JSONObject JONSData = null;
        try {
            String result = HttpClientUtil.dopost(StringUtil.urlOne + "buySetvice.do", mapParams);
            result = StringUtil.decryptAES(result);
            JONSData = JSON.parseObject(result);
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
            JONSData = StringUtil.catchMap(StringUtil.SERVICESERRO, StringUtil.STATUS500);
        }
        return JONSData;
    }


    //进入likes页面
    @RequestMapping(value = "/goLikesHtml", method = RequestMethod.GET)
    public String goLikesHtml(@RequestParam("package_name") String package_name,
                              @RequestParam("user_id") String user_id,
                              @RequestParam("user_name") String user_name) {
        String html = "";
        if (user_id.equals("0")) {
            html = "tourists_likes";
        } else {
            html = "likes";
        }
        return html;
    }


    //likesInfo，获取帖子信息
    @ResponseBody
    @RequestMapping(value = "/likesInfo", method = RequestMethod.GET)
    public JSONObject likesInfo(@RequestParam("package_name") String package_name,

                                @RequestParam("user_id") String user_id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> mapParams = new HashMap<String, String>();
        Map<String, String> mapInitializeParams = new HashMap<String, String>();
        mapInitializeParams.put("user_id", user_id);
        mapInitializeParams.put("package_name", package_name);
        String jsonParams = JSON.toJSONString(mapInitializeParams,
                SerializerFeature.WriteMapNullValue).toString();//组装要传送的参数
        mapParams.put("token", StringUtil.encryptAES(jsonParams));

        JSONObject JONSData = null;
        try {
            String result = HttpClientUtil.dopost(StringUtil.urlOne + "userMedia.do", mapParams);
            result = StringUtil.decryptAES(result);
            JONSData = JSON.parseObject(result);
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
            JONSData = StringUtil.catchMap(StringUtil.SERVICESERRO, StringUtil.STATUS500);
        }
        return JONSData;
    }


    //loadMedia，获取新的帖子信息
    @ResponseBody
    @RequestMapping(value = "/loadMorePage", method = RequestMethod.GET)
    public JSONObject loadMorePage(@RequestParam("package_name") String package_name,

                                   @RequestParam("user_id") String user_id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> mapParams = new HashMap<String, String>();
        Map<String, String> mapInitializeParams = new HashMap<String, String>();
        mapInitializeParams.put("user_id", user_id);
        mapInitializeParams.put("package_name", package_name);
        mapInitializeParams.put("need_update", "2");
        mapInitializeParams.put("data_dic", "1");
        String jsonParams = JSON.toJSONString(mapInitializeParams,
                SerializerFeature.WriteMapNullValue).toString();//组装要传送的参数

        mapParams.put("token", StringUtil.encryptAES(jsonParams));
        JSONObject JONSData = null;
        try {
            String result = HttpClientUtil.dopost(StringUtil.urlTwo + "userMoreMedia.do", mapParams);
            result = StringUtil.decryptAES(result);
            JONSData = JSON.parseObject(result);
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
            JONSData = StringUtil.catchMap(StringUtil.SERVICESERRO, StringUtil.STATUS500);
        }

        return JONSData;
    }



    //likesInfo，获取帖子信息
    @ResponseBody
    @RequestMapping(value = "/setupScrollLabelInfo", method = RequestMethod.GET)
    public JSONObject setupScrollLabelInfo(
                                ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> mapParams = new HashMap<String, String>();
        Map<String, String> mapInitializeParams = new HashMap<String, String>();

        String jsonParams = JSON.toJSONString(mapInitializeParams,
                SerializerFeature.WriteMapNullValue).toString();//组装要传送的参数
        mapParams.put("token", StringUtil.encryptAES(jsonParams));
        JSONObject JONSData = null;
        try {
            String result = HttpClientUtil.dopost(StringUtil.urlTwo + "versionTwoSetupScrollLabelInfo.do", mapParams);
            result = StringUtil.decryptAES(result);
            result=result.replace("]","");
            result=result.replace("[","");
            JONSData = JSON.parseObject(result);
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
            JONSData = StringUtil.catchMap(StringUtil.SERVICESERRO, StringUtil.STATUS500);
        }
        return JONSData;
    }
}
