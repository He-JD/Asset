package com.xgq.config;


import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.xgq.common.ShiroData;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
   * @author HeJD
   * @date 2018/9/18 16:56
   * @Description:MyPlus字段自动填充器
   */

@Slf4j
@Component
public class SysMetaObjectHandler extends MetaObjectHandler {

    /**新增填充*/
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("===========正在调用该insert填充字段方法==============");
        Object createDate = getFieldValByName("createDate",metaObject);
        Object updateDate = getFieldValByName("updateDate",metaObject);
        Object createId = getFieldValByName("createId",metaObject);
        Object updateId = getFieldValByName("updateId",metaObject);

        if (null == createDate) {
            setFieldValByName("createDate", new Date(),metaObject);
        }
        if (null == updateDate) {
            setFieldValByName("updateDate", new Date(),metaObject);
        }
        if (createId==null){
            if(ShiroData.ShiroUser()!=null){
                setFieldValByName("createId",ShiroData.getId(),metaObject);
            }
        }
        if (updateId==null){
            if(ShiroData.ShiroUser()!=null){
                setFieldValByName("updateId",ShiroData.getId(),metaObject);
            }
        }
    }

    /**更新填充*/
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("==========正在调用该update填充字段方法==============");
        Object updateDate = getFieldValByName("updateDate",metaObject);
        Object updateId = getFieldValByName("updateId",metaObject);
        if (null == updateDate) {
            setFieldValByName("updateDate", new Date(),metaObject);
        }
        if (updateId==null){
            if(ShiroData.ShiroUser()!=null){
                setFieldValByName("updateId",ShiroData.getId(),metaObject);
            }
        }
    }
}
