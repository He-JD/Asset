package com.xgq.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xgq.common.ServerResponse;
import com.xgq.dao.SysRoleDao;
import com.xgq.dao.SysUserDao;
import com.xgq.entity.SysRole;
import com.xgq.entity.SysUser;
import com.xgq.service.ILayUIService;
import com.xgq.service.ISysRoleService;
import com.xgq.vo.layui.XgqWebLayuiTableVo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeJD
 * @since 2018-10-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private ILayUIService iLayUIService;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public XgqWebLayuiTableVo findRoleListByMap(Integer page, Integer limit, Map<String, Object> map) {
        EntityWrapper<SysRole> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("del_flag",false);
        if (!map.isEmpty()){
           entityWrapper.like("name",map.get("key").toString());
        }
        System.out.println("======================================分页查询=================================");
        Page<SysRole> sysRolePage=selectPage(new Page<>(page,limit),entityWrapper);
        return iLayUIService.assembleXgqWebLayuiTableVo(assembleRole(sysRolePage.getRecords()),sysRolePage.getTotal());
    }

    @Override
    public ServerResponse modifyRole(SysRole sysRole) {
        if (findCountByRoleName(sysRole.getName())>0){
            return ServerResponse.createByErrorMessage("角色名已存在");
        }
         sysRoleDao.updateById(sysRole);
         sysRoleDao.delecteSysRoleMenusById(sysRole.getId());
         int result=sysRoleDao.insertSysRoleMenus(sysRole.getId(),sysRole.getMenuSet());
        if (result>0){
           return ServerResponse.createBySuccessMessage("修改成功");
        }
         return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public SysRole findSysRoleById(Integer id) {
          return sysRoleDao.selectSysRoleById(id);

    }


    private Integer findCountByRoleName(String roleName){
       return   sysRoleDao.selectCount(new EntityWrapper<SysRole>()
                    .eq("del_flag",false)
                    .eq("name",roleName));
    }

    private List<SysRole> assembleRole(List<SysRole> sysRoleList){
        if (!CollectionUtils.isEmpty(sysRoleList)){
            for (SysRole sysRole : sysRoleList) {
                SysUser creteUser=sysUserDao.selectById(sysRole.getCreateId());
                sysRole.setCreateUser(creteUser);

                SysUser updateUser=sysUserDao.selectById(sysRole.getUpdateId());
                sysRole.setUpdateUser(updateUser);
            }
        }
        return sysRoleList;
    }
}
