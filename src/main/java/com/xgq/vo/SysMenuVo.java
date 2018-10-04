package com.xgq.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: HeJD
 * @Date: 2018/9/27 14:43
 * @Description:
 */
@Data
public class SysMenuVo implements Serializable {

    private Long           id;
    private Long           pid;
    private String         title;
    private String         icon;
    private String         href;
    private Boolean        spread   = false;
    private List<SysMenuVo> children = Lists.newArrayList();
}
