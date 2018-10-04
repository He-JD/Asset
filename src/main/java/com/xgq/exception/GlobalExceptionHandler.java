package com.xgq.exception;

import com.alibaba.fastjson.JSONObject;
import com.xgq.common.ServerResponse;
import com.xgq.enums.ExceptionEnum;
import com.xgq.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @Auther: HeJD
 * @Date: 2018/9/14 11:37
 * @Description:全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ServerResponse exceptionHandle(Exception e){
    log.error("异常信息 {}",e.getMessage());
    return ServerResponse.createByErrorCodeMessage(ExceptionEnum.UNKONW_ERROR.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public ServerResponse BusinessException(HttpServletRequest request,BusinessException e){
    log.error("异常信息：{}",e.getMsg());
    printErrorMsg(request,e);
    return ServerResponse.createByErrorCodeMessage(ExceptionEnum.ERROR.getCode(),e.getMsg());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ModelAndView unauthorizedException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              UnauthorizedException e){
        if(ToolUtil.isAjax(request)){
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter printWriter= response.getWriter();
                ServerResponse serverResponse=ServerResponse.createByErrorMessage("您无此权限,请联系管理员");
                printWriter.write(JSONObject.toJSONString(serverResponse));
                printWriter.flush();
                printWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else {
            return new ModelAndView("admin/error/500","message",e.getMessage());
        }
        return null;
    }







    /**
     * 打印异常信息
     */
    private void printErrorMsg(HttpServletRequest request, Exception ex) {
        log.error("************************异常开始*******************************");
        log.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        log.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            log.error(name + "---" + request.getParameter(name));
        }
        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            log.error(stackTraceElement.toString());
        }
        log.error("************************异常结束*******************************");
    }


}
