package com.xgq.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xgq.common.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author HeJD
 * @since 2018-09-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenu extends TreeEntity<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String href;
    /**
     * 打开方式
     */
    private String target;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 显示背景色
     */
    private String bgColor;
    /**
     * 是否显示
     */
    @TableField(value = "is_show",strategy = FieldStrategy.IGNORED)
    private Boolean isShow;
    /**
     * 权限标识
     */
    private String permission;

    @TableField(exist = false)
    private Long menuCount;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PARENT_ID = "parent_id";

    public static final String LEVEL = "level";

    public static final String PARENT_IDS = "parent_ids";

    public static final String SORT = "sort";

    public static final String HREF = "href";

    public static final String TARGET = "target";

    public static final String ICON = "icon";

    public static final String BG_COLOR = "bg_color";

    public static final String IS_SHOW = "is_show";

    public static final String PERMISSION = "permission";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
