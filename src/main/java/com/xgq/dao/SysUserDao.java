package com.xgq.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xgq.entity.SysUser;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HeJD
 * @since 2018-09-21
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    SysUser selectUserByMap(Map<String, Object> map);


    Map selectUserMenuCount();
}
