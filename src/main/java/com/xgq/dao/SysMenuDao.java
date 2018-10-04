package com.xgq.dao;

import com.xgq.entity.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xgq.vo.SysMenuVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HeJD
 * @since 2018-09-27
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {

    List<SysMenuVo> selectMenuByUser(Map<String,Object> map);


    List<SysMenu> selectMenuListByMap(Map<String,Object> map);
}
