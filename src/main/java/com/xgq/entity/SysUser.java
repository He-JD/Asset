package com.xgq.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.google.common.collect.Sets;
import com.xgq.common.DataEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author HeJD
 * @since 2018-09-29
 */
@Data
public class SysUser extends DataEntity<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 昵称
     */
    private String nickName;
    private String icon;
    /**
     * 密码
     */
    private String password;
    /**
     * shiro加密盐
     */
    private String salt;
    /**
     * 手机号码
     */
    private String tel;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 是否锁定
     */
    private Integer locked;

    @TableField(exist = false)
    private Set<SysRole> sysRoleSet = Sets.newHashSet();

    @TableField(exist = false)
    private Set<SysMenu> sysMenuSet= Sets.newHashSet();

    public static final String ID = "id";

    public static final String LOGIN_NAME = "login_name";

    public static final String NICK_NAME = "nick_name";

    public static final String ICON = "icon";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String TEL = "tel";

    public static final String EMAIL = "email";

    public static final String LOCKED = "locked";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
