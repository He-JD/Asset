package com.xgq.controller;

import com.xgq.annotation.SysLog;
import com.xgq.common.ServerResponse;
import com.xgq.entity.SysMenu;
import com.xgq.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: HeJD
 * @Date: 2018/9/29 16:35
 * @Description:
 */
@Api(tags = "菜单管理模块")
@Slf4j
@Controller
@RequestMapping("/admin/system/")
public class SysMenuController {

    @Autowired
    private ISysMenuService iSysMenuService;

    @ApiOperation(value = "跳转菜单列表页面")
    @GetMapping("menu/list")
    @SysLog("跳转菜单列表")
    public String list()
    {
           return "admin/system/menu/test";
    }

    @ApiOperation(value = "得到菜单树")
    @RequiresPermissions("sys:menu:list")
    @GetMapping("menu/tree")
    @ResponseBody
    public ServerResponse treeList(){
        return iSysMenuService.findSysMenuList();
    }

    @ApiOperation(value = "跳转添加菜单页面")
    @GetMapping("menu/add_ftl")
    public String toMenuAdd(@RequestParam(value = "parentId",required = false) String parentId, Model model){
        if (parentId!=null){
           SysMenu parentMenu= iSysMenuService.selectById(parentId);
           model.addAttribute("parentMenu",parentMenu);
        }
        return "admin/system/menu/add";
    }

    @ApiOperation(value = "跳转编辑菜单页面")
    @GetMapping("menu/edit_ftl")
    public String toMenuEdit(String id, Model model){
            SysMenu sysMenu= iSysMenuService.selectById(id);
            model.addAttribute("menu",sysMenu);
        return "admin/system/menu/edit";
    }

    @ApiOperation(value = "添加菜单")
    @SysLog("添加菜单")
    @RequiresPermissions("sys:menu:add")
    @PostMapping("menu")
    @ResponseBody
    public ServerResponse addMenu(SysMenu sysMenu){
        return iSysMenuService.addMenu(sysMenu);
    }

    @ApiOperation(value = "删除菜单")
    @SysLog("删除菜单")
    @RequiresPermissions("sys:menu:delete")
    @PutMapping("menu/{id}")
    @ResponseBody
    public ServerResponse deleteMenu(@PathVariable("id") Integer id){
        return iSysMenuService.removeSysMenu(id);
    }

    @ApiOperation(value = "编辑菜单")
    @SysLog("编辑菜单")
    @RequiresPermissions("sys:menu:edit")
    @PutMapping("menu")
    @ResponseBody
    public ServerResponse editMenue(SysMenu sysMenu){
        return iSysMenuService.modifyMenu(sysMenu);
    }

}
