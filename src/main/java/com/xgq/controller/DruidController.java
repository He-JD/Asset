package com.xgq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: HeJD
 * @Date: 2018/9/28 14:42
 * @Description:
 */
@Controller
@RequestMapping("/admin/druid/")
public class DruidController {


    @GetMapping("list")
    public String index(){
        return  "admin/system/druid/index";
    }
}

