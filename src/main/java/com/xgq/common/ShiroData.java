package com.xgq.common;

import com.xgq.config.AuthRealm.ShiroUser;
import org.apache.shiro.SecurityUtils;

/**
 * @Auther: HeJD
 * @Date: 2018/9/22 11:33
 * @Description:
 */
public class ShiroData {
    /**
     * 取出Shiro中的当前用户LoginName.
     */
    public static String getIcon() {
        return ShiroUser().getIcon();
    }

    public static Long getId() {
        return ShiroUser().getId();
    }

    public static String getloginName() {
        return ShiroUser().getloginName();
    }

    public static String getNickName(){
        return ShiroUser().getNickName();
    }

    public static ShiroUser ShiroUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

}
