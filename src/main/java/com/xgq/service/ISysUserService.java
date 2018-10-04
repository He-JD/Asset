package com.xgq.service;

import com.baomidou.mybatisplus.service.IService;
import com.xgq.entity.SysUser;
import com.xgq.vo.SysMenuVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HeJD
 * @since 2018-09-21
 */
public interface ISysUserService extends IService<SysUser> {

    /**
       * @author HeJD
       * @date 2018/9/22 8:55
       * @param [userName]
       * @return com.xgq.entity.SysUser
       * @Description: 查询用户-用户角色-用户权限
       */
    SysUser findUserByLoginName(String userName);

    List<SysMenuVo> getMenuByUser(Long userId);
}
