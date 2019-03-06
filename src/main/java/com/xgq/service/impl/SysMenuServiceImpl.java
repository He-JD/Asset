package com.xgq.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.xgq.common.ServerResponse;
import com.xgq.dao.SysMenuDao;
import com.xgq.entity.SysMenu;
import com.xgq.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
 * @since 2018-09-27
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Cacheable(value = "ASSET_ALL_MENU", unless = "#result == null")
    @Override
    public ServerResponse findSysMenuList() {
        Map<String,Object> map= Maps.newHashMap();
        map.put("parentId",null);
        List<SysMenu> sysMenuList =sysMenuDao.selectMenuListByMap(map);
        return ServerResponse.createBySuccess(sysMenuList);
    }

    @CacheEvict(value = "ASSET_ALL_MENU",allEntries = true)
    @Override
    public ServerResponse addMenu(SysMenu sysMenu) {
        if (findCountByMenuName(sysMenu.getName())>0){
            return ServerResponse.createByErrorMessage("菜单名称已存在");
        }
        if (findCountByPermisson(sysMenu.getPermission())>0){
            return ServerResponse.createByErrorMessage("权限标识已存在");
        }
        if (sysMenu.getParentId()!=null){
            SysMenu parentMenu=sysMenuDao.selectById(sysMenu.getParentId());
            sysMenu.setParentId(parentMenu.getId());
            sysMenu.setLevel(parentMenu.getLevel()+1);
            sysMenu.setParentIds(parentMenu.getParentIds()+",");
            Object o = selectObj(Condition.create().setSqlSelect("max(sort)").eq("parent_id",sysMenu.getParentId()));
            int sort = 0;
            if(o != null){
                sort =  (Integer)o +10;
            }
            sysMenu.setSort(sort);

        }else{
            sysMenu.setLevel(1);
            Object o = selectObj(Condition.create().setSqlSelect("max(sort)").isNull("parent_id"));
            int sort = 0;
            if(o != null){
                sort =  (Integer)o +10;
            }

            sysMenu.setSort(sort);
        }
        Integer result=sysMenuDao.insert(sysMenu);
        if (result>0){
            return ServerResponse.createBySuccessMessage("添加菜单成功");
        }
        return ServerResponse.createByErrorMessage("添加菜单失败");
    }

    @CacheEvict(value = "ASSET_ALL_MENU",allEntries = true)
    @Override
    public ServerResponse removeSysMenu(Integer id) {
        SysMenu sysMenu= sysMenuDao.selectById(id);
        sysMenu.setDelFlag(true);
        int result=sysMenuDao.updateById(sysMenu);
        if (result>0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @CacheEvict(value = "ASSET_ALL_MENU",allEntries = true)
    @Override
    public ServerResponse modifyMenu(SysMenu sysMenu) {
        if (findCountByMenuName(sysMenu.getName())>0){
            return ServerResponse.createByErrorMessage("菜单名称已存在");
        }
        if (findCountByPermisson(sysMenu.getPermission())>0){
            return ServerResponse.createByErrorMessage("权限标识已存在");
        }
        int result=  sysMenuDao.updateById(sysMenu);
        if (result>0){
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }


    private Integer findCountByPermisson(String permission) {
        EntityWrapper<SysMenu> wrapper=new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        wrapper.eq("permission",permission);
        return sysMenuDao.selectCount(wrapper);
    }


    private Integer findCountByMenuName(String menuName){
        EntityWrapper<SysMenu> wrapper=new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        wrapper.eq("name",menuName);
        return sysMenuDao.selectCount(wrapper);
    }


}
