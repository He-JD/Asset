package com.xgq.service;

import com.baomidou.mybatisplus.service.IService;
import com.xgq.common.ServerResponse;
import com.xgq.entity.SysRole;
import com.xgq.vo.layui.XgqWebLayuiTableVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HeJD
 * @since 2018-10-06
 */
public interface ISysRoleService extends IService<SysRole> {

      XgqWebLayuiTableVo findRoleListByMap(Integer page, Integer limit, Map<String,Object> map);

     ServerResponse modifyRole(SysRole sysRole);

      SysRole findSysRoleById(Integer id);
}
