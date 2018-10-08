package com.xgq.service;

import com.xgq.vo.layui.XgqWebLayuiTableVo;

import java.util.List;

/**
 * @Auther: HeJD
 * @Date: 2018/10/6 13:54
 * @Description:
 */
public interface ILayUIService {

    /**
     * 返回Layui数据表格所需要的数据格式
     * @param list   表格所需数据
     * @param count  数据总条数
     * @return
     * @date 2018年7月12日
     * @author HeJD
     */
    XgqWebLayuiTableVo assembleXgqWebLayuiTableVo(List<?> list, Long count);

}
