package com.xgq.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
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
public class SysRole extends DataEntity<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    private String name;

    public static final String ID = "id";

    public static final String NAME = "name";

    @TableField(exist = false)
    private Set<SysMenu> menuSet;

    @TableField(exist = false)
    private Set<SysUser> userSet;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
