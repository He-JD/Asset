package com.xgq.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.xgq.annotation.SysLog;
import com.xgq.common.ServerResponse;
import com.xgq.entity.UserInfo;
import com.xgq.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HeJD
 * @since 2018-09-17
 */
@Api(value = "用户模块接口2",tags = "项目demo接口（保留）")
@RestController
@RequestMapping("/userInfo/")
public class UserInfoController {

    @Autowired
    private IUserInfoService iUserInfoService;

    @ApiOperation(value = "得到所有用户信息",notes = "查询所有用户信息")
    @GetMapping("getAll")
    @SysLog("得到所有用户信息")
    @Cacheable(value = "UserInfoList",keyGenerator = "simpleKeyGenerator")
    public ServerResponse getAll(){
     List<UserInfo> userInfoList= iUserInfoService.selectList(null);
     return ServerResponse.createBySuccess(userInfoList);
    }

    @ApiOperation(value = "查询某个用户",notes = "根据Id查询用户")
    @ApiImplicitParam( name = "id",value = "用户id",paramType = "path",dataType="Integer")
    @GetMapping("get/{id}")
    @SysLog("查询某个用户")
    @Cacheable(value = "user",key = "#id")
    public ServerResponse get(@PathVariable("id") Integer id){
      UserInfo userInfo= iUserInfoService.selectById(id);
      return   ServerResponse.createBySuccess(userInfo);
    }

    @ApiOperation(value="添加用户信息",notes = "添加用户数据到数据库")
    @PostMapping("add")
    @SysLog("添加用户信息")
    public ServerResponse add(UserInfo userInfo){
     boolean result=iUserInfoService.insert(userInfo);
     if (result){
         return ServerResponse.createBySuccessMessage("添加成功");
        }else {
         return ServerResponse.createByErrorMessage("添加失败");
     }
    }

    @ApiOperation( value = "更新用户信息",notes = "根据用户id更新用户数据")
    @PutMapping("updateUser")
    @SysLog("更新用户信息")
    @CachePut(value = "user",key = "#userInfo.id")
    public ServerResponse update(UserInfo userInfo){

        return iUserInfoService.updateUserInfo(userInfo);
    }

    @ApiOperation( value = "删除用户信息",notes = "根据用户id删除用户数据")
    @ApiImplicitParam(name = "id",value = "用户id",paramType = "path",dataType = "Integer")
    @DeleteMapping("delete/{id}")
    @SysLog("删除用户信息")
    @CacheEvict(value = "user",key = "#id")
    public ServerResponse delete(@PathVariable("id") Integer id){
        Boolean bool= iUserInfoService.deleteById(id);
        return ServerResponse.createBySuccess(bool);
    }

    @GetMapping("/page")
    public Map<String,Object> pageUser(){
        //分页
        Page<UserInfo> page = new Page<>(1, 3);
        Map<String,Object> result = new HashMap<>();
        result.put("respCode", "01");
        result.put("respMsg", "成功");
        result.put("data", iUserInfoService.selectPage(page));
        return result;
    }

}
