package com.xgq.controller;

import com.xgq.common.ShiroData;
import com.xgq.service.ISysUserService;
import com.xgq.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: HeJD
 * @Date: 2018/9/27 14:11
 * @Description:
 */
@Controller
@RequestMapping("admin/system/user")
public class UserController {

    @Autowired
    private ISysUserService iSysUserService;


    @GetMapping("menu")
    @ResponseBody
    public List<SysMenuVo> getUserMenu(){
        Long userId  =ShiroData.getId();
        return iSysUserService.getMenuByUser(userId);
    }





}
