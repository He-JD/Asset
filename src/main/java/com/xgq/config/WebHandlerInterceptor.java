package com.xgq.config;

import com.xgq.common.ShiroData;
import com.xgq.entity.SysUser;
import com.xgq.service.ISysUserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: HeJD
 * @Date: 2018/9/19 15:16
 * @Description:自定义拦截器
 */
public class WebHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (iSysUserService == null  ) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            iSysUserService = (ISysUserService) factory.getBean("iSysUserService");
        }

       SysUser sysUser= iSysUserService.findUserByLoginName(ShiroData.getloginName());
       if (sysUser!=null){
           request.setAttribute("currentUser",sysUser);
           return true;
       }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
