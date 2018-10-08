package com.xgq.controller;

import com.xgq.annotation.SysLog;
import com.xgq.common.ServerResponse;
import com.xgq.entity.SysMenu;
import com.xgq.entity.SysRole;
import com.xgq.service.ISysMenuService;
import com.xgq.service.ISysRoleService;
import com.xgq.vo.layui.XgqWebLayuiTableVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: HeJD
 * @Date: 2018/10/6 11:32
 * @Description:
 */
@Api(tags = "角色管理模块")
@Controller
@RequestMapping("admin/system/")
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private ISysMenuService iSysMenuService;

    @ApiOperation(value = "跳转角色列表页面")
    @SysLog("跳转角色列表")
    @RequiresPermissions("sys:role:list")
    @GetMapping("role/list")
    public String list_ftl(){
        return "admin/system/role/list";
    }

    @ApiOperation(value = "查询系统角色列表")
    @SysLog("查询系统角色列表")
    @GetMapping("role")
    @ResponseBody
    public XgqWebLayuiTableVo queryRoleList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                            @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                            ServletRequest request){
       Map<String,Object> map= WebUtils.getParametersStartingWith(request,"s_");
       return iSysRoleService.findRoleListByMap(page,limit,map);
    }

    @ApiOperation(value = "跳转编辑角色页面")
    @SysLog("跳转编辑角色页面")
    @RequiresPermissions("sys:role:edit")
    @GetMapping("role/edit")
    public String edit_ftl(Integer id, Model model){
        SysRole sysRole= iSysRoleService.findSysRoleById(id);
        StringBuilder menuIds = new StringBuilder();
        if(sysRole != null) {
            Set<SysMenu> menuSet = sysRole.getMenuSet();
            if (!CollectionUtils.isEmpty(menuSet)) {
                for (SysMenu m : menuSet) {
                    menuIds.append(m.getId().toString()).append(",");
                }
            }
        }
        ServerResponse serverResponse= iSysMenuService.findSysMenuList();
        model.addAttribute("role",sysRole);
        model.addAttribute("menuIds",menuIds.toString());
        model.addAttribute("menuList",serverResponse.getData());
        return "admin/system/role/edit";
    }

    @ApiOperation(value = "编辑角色信息")
    @ApiImplicitParam(name = "sysRole",required = true)
    @SysLog("编辑角色信息")
    @PutMapping("role")
    @ResponseBody
    public ServerResponse editRole(@RequestBody SysRole sysRole){
      return   iSysRoleService.modifyRole(sysRole);
    }



}
