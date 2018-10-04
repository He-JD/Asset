package com.xgq.common;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.List;

/**
 * 数据Entity类
 *
 * @author chenjianann
 *
 * @version 2014-05-16
 */
@Data
public abstract class TreeEntity<T extends Model> extends DataEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * varchar(64) NULL父id
     */
    @TableField(value = "parent_id")
    protected Long parentId;

    /**
     * 节点层次（第一层，第二层，第三层....）
     */
    protected Integer level;
    /**
     * varchar(1000) NULL路径
     */
    @TableField(value = "parent_ids")
    protected String parentIds;
    /**
     * int(11) NULL排序
     */
    protected Integer sort;

    @TableField(exist = false)
    protected List<T> children;

    @TableField(exist = false)
    protected T parentTree;



}
