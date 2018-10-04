package com.xgq.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xgq.common.ServerResponse;
import com.xgq.common.ShiroData;
import com.xgq.dao.SysUserDao;
import com.xgq.entity.SysMenu;
import com.xgq.entity.SysUser;
import com.xgq.service.ILoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: HeJD
 * @Date: 2018/9/29 13:53
 * @Description:
 */
@Service("iLoginService")
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public ServerResponse login(String loginName, String password, String rememberMe) {
        Map<String,Object> map = Maps.newHashMap();
        String error = null;
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName,password,Boolean.valueOf(rememberMe));
        try {
            user.login(token);
            if (user.isAuthenticated()) {
                map.put("url","index");
            }
        }catch (IncorrectCredentialsException e) {
            error = "登录密码错误.";
        } catch (ExcessiveAttemptsException e) {
            error = "登录失败次数过多";
        } catch (LockedAccountException e) {
            error = "帐号已被锁定.";
        } catch (DisabledAccountException e) {
            error = "帐号已被禁用.";
        } catch (ExpiredCredentialsException e) {
            error = "帐号已过期.";
        } catch (UnknownAccountException e) {
            error = "帐号不存在";
        } catch (UnauthorizedException e) {
            error = "您没有得到相应的授权！";
        }
        if(StringUtils.isBlank(error)){
            return ServerResponse.createBySuccess("登录成功",map);
        }else{
            return ServerResponse.createByErrorMessage(error);
        }
    }

    @Override
    public String mainPage(Model model) {
        Map map=Maps.newHashMap();
        map.put("id",ShiroData.getId());
        SysUser sysUser= sysUserDao.selectUserByMap(map);
        Map countMap=sysUserDao.selectUserMenuCount();
        Set<SysMenu> sysMenuSet=sysUser.getSysMenuSet();
        List<SysMenu> sysMenuList= Lists.newArrayList();
        for (SysMenu sysMenu : sysMenuSet) {
            if (StringUtils.isNotBlank(sysMenu.getHref())){
                sysMenu.setMenuCount((Long) countMap.get(sysMenu.getPermission()));
                sysMenuList.add(sysMenu);
            }
        }
        model.addAttribute("userMenu",sysMenuList);
        return "main";
    }


}
