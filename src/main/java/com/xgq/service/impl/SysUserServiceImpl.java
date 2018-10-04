package com.xgq.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.xgq.annotation.SysLog;
import com.xgq.dao.SysMenuDao;
import com.xgq.dao.SysUserDao;
import com.xgq.entity.SysUser;
import com.xgq.service.ISysUserService;
import com.xgq.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeJD
 * @since 2018-09-21
 */
@Service("iSysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysMenuDao sysMenuDao;


    @Cacheable(value = "user", key = "'user_name_'+#userName",unless = "#result == null")
    @Override
    public SysUser findUserByLoginName(String userName) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("loginName", userName);
        return sysUserDao.selectUserByMap(map);
    }

    @SysLog("得到菜单数据")
    @Cacheable(value = "allMenus",key = "'user_menu_'+T(String).valueOf(#userId)",unless = "#result == null or #result.size() == 0")
    @Override
    public List<SysMenuVo> getMenuByUser(Long userId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId",userId);
        map.put("parentId",null);
        List<SysMenuVo> sysMenuList =sysMenuDao.selectMenuByUser(map);
        return sysMenuList;
    }
}
