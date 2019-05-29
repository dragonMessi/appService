package com.adtech.basic.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * vip页面
 * */
@Controller
@RequestMapping(value = "vip")
public class VipController {

    //进入vip页面
    @RequestMapping(value = "/goVipHtml", method = RequestMethod.GET)
    public String goFollowersHtml(@RequestParam("package_name") String package_name,
                                  @RequestParam("user_id") String user_id,
                                  @RequestParam("user_name") String user_name) {

        return "vip/vip";
    }

}
