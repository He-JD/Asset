/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xgq.common;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.xgq.entity.SysUser;
import lombok.Data;

import java.util.Date;

/**
 * 数据Entity类
 *
 * @param <T>
 */
@Data
public abstract class DataEntity<T extends Model> extends Model<T> {

    private static final long serialVersionUID = 1L;

    /**
     *  创建者
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected Long createId;

    /**
     * 创建日期
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    protected Date createDate;

    /**
     * 更新者
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    protected Long updateId;

    /**
     * 更新日期
      */
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    protected Date updateDate;

    /**
     * 删除标记（Y：正常；N：删除；A：审核；）
     */
    @TableField(value = "del_flag")
    protected Boolean delFlag=false;

    /**
     * 备注
     */
    @TableField(strategy= FieldStrategy.IGNORED)
    protected String remarks;

    /**
     * 创建着
     */
    @TableField(exist = false)
    protected SysUser createUser;

    /**
     * 修改者
     */
    @TableField(exist = false)
    protected SysUser updateUser;



}
