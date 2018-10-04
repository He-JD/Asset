package com.xgq.config;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.xgq.common.Constants;
import com.xgq.entity.SysMenu;
import com.xgq.entity.SysRole;
import com.xgq.entity.SysUser;
import com.xgq.service.ISysUserService;
import com.xgq.util.Encodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Set;

/**
 * @Auther: HeJD
 * @Date: 2018/9/21 16:15
 * @Description:自定义Realm
 */
@Slf4j
@Component(value = "authRealm")
public class AuthRealm extends AuthorizingRealm {

    /** 使用懒加载，否则切面无效*/
    @Autowired
    @Lazy
    private ISysUserService iSysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("=====================shiro开始授权=====================");
        ShiroUser shiroUser= (ShiroUser)principalCollection.getPrimaryPrincipal();
        SysUser sysUser= iSysUserService.findUserByLoginName(shiroUser.getloginName());
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        Set<SysRole> sysRoleSet= sysUser.getSysRoleSet();

        Set<String> roles= Sets.newHashSet();
        for (SysRole sysRole : sysRoleSet) {
            if(StringUtils.isNotBlank(sysRole.getName())) {
                roles.add(sysRole.getName());
            }
        }

        Set<SysMenu> sysMenuSet= sysUser.getSysMenuSet();
        Set<String> permissions=Sets.newHashSet();
        for (SysMenu sysMenu : sysMenuSet) {
            if(StringUtils.isNotBlank(sysMenu.getPermission())){
                permissions.add(sysMenu.getPermission());
            }
        }

        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=====================shiro开始认证=====================");
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        String userName=(String)token.getPrincipal();
        SysUser sysUser= iSysUserService.findUserByLoginName(userName);
        if (sysUser==null){
            throw new UnknownAccountException();
        }
        byte [] salt=Encodes.decodeHex(sysUser.getSalt());
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(
                new ShiroUser(sysUser.getId(),sysUser.getLoginName(),sysUser.getNickName(),sysUser.getIcon()),
                sysUser.getPassword(),
                ByteSource.Util.bytes(salt),
                getName()
                );

        return simpleAuthenticationInfo;
    }


    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Constants.HASH_ALGORITHM);
        matcher.setHashIterations(Constants.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public Long id;
        public String loginName;
        public String nickName;
        public String icon;

        public ShiroUser(Long id, String loginName, String nickName,String icon) {
            this.id = id;
            this.loginName = loginName;
            this.nickName = nickName;
            this.icon=icon;
        }

        public String getloginName() {
            return loginName;
        }
        public String getNickName() {
            return nickName;
        }
        public String getIcon() {
            return icon;
        }
        public Long getId() {
            return id;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return nickName;
        }

        /**
         * 重载hashCode,只计算loginName;
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(loginName);
        }

        /**
         * 重载equals,只计算loginName;
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ShiroUser other = (ShiroUser) obj;
            if (loginName == null) {
                return other.loginName == null;
            } else {
                return loginName.equals(other.loginName);
            }
        }
    }


}
