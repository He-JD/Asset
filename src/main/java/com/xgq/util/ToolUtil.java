package com.xgq.util;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.xiaoleilu.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Auther: HeJD
 * @Date: 2018/9/18 15:41
 * @Description:通用工具类
 */
@Slf4j
public class ToolUtil {

    /**
     * 获取客户端的ip信息
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        log.info("ipadd : " + ip);
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.info(" ip --> " + ip);
        return ip;
    }

    /***
     * 腾讯WebService API
     * http://lbs.qq.com/webservice_v1/guide-ip.html
     * @param ip
     * @return
     */
    public static Map<String,String> getAddressByIP(String ip) {
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "0.0.0.0";
        }
        Map<String,String> map = Maps.newHashMap();
        StringBuilder sb = new StringBuilder("https://apis.map.qq.com/ws/location/v1/ip?key=N7XBZ-NX764-OFOUH-D5LJY-KZ3QK-6WFNX&ip=");
        sb.append(ip);
        String result= HttpUtil.get(sb.toString(), "UTF-8");
        Map resultMap = JSON.parseObject(result,Map.class);
        Integer status = (Integer) resultMap.get("status");
        Map finalMap = Maps.newHashMap();
        if(status == 0){
            Map m = (Map) resultMap.get("result");
            Map<String,String> detail = (Map<String,String>)m.get("ad_info");
            String area = detail.get("nation");
            String isp = "";
            String province = detail.get("province");
            String city = detail.get("city");
            finalMap.put("isp",isp);
            if(StringUtils.isNotBlank(area)){
                finalMap.put("area",area);
            }else {
                finalMap.put("area","");
            }
            if(StringUtils.isNotBlank(province)){
                finalMap.put("province",province);
            }else {
                finalMap.put("province","");
            }
            if(StringUtils.isNotBlank(city)){
                finalMap.put("city",city);
            }else{
                finalMap.put("city","");
            }
        }else{
            finalMap.put("area","未知");
            finalMap.put("isp","未知");
            finalMap.put("province","未知");
            finalMap.put("city","未知");
        }
        return finalMap;
    }

    /**
     * 判断请求是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        String accept = request.getHeader("accept");
        return accept != null && accept.contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }

    /**
     * 获取操作系统,浏览器及浏览器版本信息
     * @param request
     * @return
     */
    public static Map<String,String> getOsAndBrowserInfo(HttpServletRequest request){
        Map<String,String> map = Maps.newHashMap();
        String  browserDetails  =   request.getHeader("User-Agent");
        String  userAgent       =   browserDetails;
        String  user            =   userAgent.toLowerCase();

        String os = "";
        String browser = "";

        //=================OS Info=======================
        if (userAgent.toLowerCase().contains("windows"))
        {
            os = "Windows";
        } else if(userAgent.toLowerCase().contains("mac"))
        {
            os = "Mac";
        } else if(userAgent.toLowerCase().contains("x11"))
        {
            os = "Unix";
        } else if(userAgent.toLowerCase().contains("android"))
        {
            os = "Android";
        } else if(userAgent.toLowerCase().contains("iphone"))
        {
            os = "IPhone";
        }else{
            os = "UnKnown, More-Info: "+userAgent;
        }
        //===============Browser===========================
        if (user.contains("edge"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]
                    + "-" +(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera")){
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]
                        +"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            }else if(user.contains("opr")){
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                        .replace("OPR", "Opera");
            }

        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6"))  ||
                (user.contains("mozilla/4.7")) || (user.contains("mozilla/4.78")) ||
                (user.contains("mozilla/4.08")) || (user.contains("mozilla/3")) )
        {
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
            browser="IE" + IEVersion.substring(0,IEVersion.length() - 1);
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }
        map.put("os",os);
        map.put("browser",browser);
        return map;
    }


    public static void main(String[] args) {
        Map<String,String> map=   getAddressByIP("");
        String area=map.get("area");
        String province= map.get("province");
        String city=map.get("city");
        System.out.println(area+"--"+province+"--"+city);
        String ste=  JSON.toJSONString(map);
        System.out.println(ste);
    }

}
