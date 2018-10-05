package com.xgq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: HeJD
 * @Date: 2018/9/28 14:42
 * @Description:
 */
@Api(tags = "Druid数据监控")
@Controller
@RequestMapping("/admin/druid/")
public class DruidController {

    @ApiOperation(value = "跳转数据库监控页面")
    @GetMapping("list")
    public String index(){
        return  "admin/system/druid/index";
    }
}

