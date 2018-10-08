package com.xgq.controller;


import com.xgq.annotation.SysLog;
import com.xgq.common.Constants;
import com.xgq.common.ServerResponse;
import com.xgq.service.ILoginService;
import com.xgq.util.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HeJD
 * @since 2018-09-21
 */
@Api(tags = "用户登录模块")
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    @ApiOperation(value = "跳转登录页")
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        log.info("跳到这边的路径为:"+request.getRequestURI());
        Subject s = SecurityUtils.getSubject();
        log.info("是否记住登录--->"+s.isRemembered()+"<-----是否有权限登录----->"+s.isAuthenticated()+"<----");
        if(s.isAuthenticated()){
            return "redirect:index";
        }else {
            return "login";
        }
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("login/main")
    @ResponseBody
    @SysLog("用户登录")
    public ServerResponse login(String loginName,String password,String rememberMe){
        return  iLoginService.login(loginName,password,rememberMe);
    }

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @ApiOperation(value = "获取验证码图片")
    @GetMapping("/genCaptcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
        log.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    @ApiOperation(value = "跳转首页")
    @GetMapping("/index")
    public String showView(Model model){
        return "index";
    }

    @ApiOperation(value = "跳转主页")
    @GetMapping("/main")
    public String mainPage(Model model){
        return  iLoginService.mainPage(model);
    }

}

