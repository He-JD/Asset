package com.xgq.controller;

import com.xgq.annotation.SysLog;
import com.xgq.common.ServerResponse;
import com.xgq.entity.SysMenu;
import com.xgq.service.ISysMenuService;
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
@Slf4j
@Controller
@RequestMapping("/admin/system/")
public class SysMenuController {

    @Autowired
    private ISysMenuService iSysMenuService;

    @GetMapping("menu/list")
    @SysLog("跳转菜单列表")
    public String list()
    {
           return "admin/system/menu/test";
    }

    @RequiresPermissions("sys:menu:list")
    @GetMapping("menu/tree")
    @ResponseBody
    public ServerResponse treeList(){
        return iSysMenuService.findSysMenuList();
    }

    @GetMapping("menu")
    public String getMenu(@RequestParam(value = "parentId",required = false) String parentId, Model model){
        if (parentId!=null){
           SysMenu parentMenu= iSysMenuService.selectById(parentId);
           model.addAttribute("parentMenu",parentMenu);
        }
        return "admin/system/menu/add";
    }

    @SysLog("添加菜单")
    @RequiresPermissions("sys:menu:add")
    @PostMapping("menu")
    @ResponseBody
    public ServerResponse addMenu(SysMenu sysMenu){
        return iSysMenuService.addMenu(sysMenu);
    }

    @SysLog("删除菜单")
    @RequiresPermissions("sys:menu:delete")
    @PutMapping("menu/{id}")
    @ResponseBody
    public ServerResponse deleteMenu(@PathVariable("id") Integer id){
        return iSysMenuService.modifySysMenu(id);
    }

}
