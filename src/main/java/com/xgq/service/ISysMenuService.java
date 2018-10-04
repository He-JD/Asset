package com.xgq.service;

import com.xgq.common.ServerResponse;
import com.xgq.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HeJD
 * @since 2018-09-27
 */
public interface ISysMenuService extends IService<SysMenu> {

    ServerResponse findSysMenuList();

    ServerResponse addMenu(SysMenu sysMenu);

    ServerResponse modifySysMenu(Integer id);
}
