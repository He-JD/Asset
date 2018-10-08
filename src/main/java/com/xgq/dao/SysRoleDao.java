package com.xgq.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xgq.entity.SysMenu;
import com.xgq.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HeJD
 * @since 2018-10-06
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

     SysRole selectSysRoleById(@Param("id") Integer id);

     void delecteSysRoleMenusById(@Param("id") Long id);


     int insertSysRoleMenus(@Param("id") Long id, @Param("menuSet") Set<SysMenu> menuSet);
}
