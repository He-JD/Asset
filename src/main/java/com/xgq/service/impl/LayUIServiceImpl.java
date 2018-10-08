package com.xgq.service.impl;

import com.xgq.common.Constants;
import com.xgq.service.ILayUIService;
import com.xgq.vo.layui.XgqWebLayuiTableVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: HeJD
 * @Date: 2018/10/6 13:55
 * @Description:
 */
@Service
public class LayUIServiceImpl implements ILayUIService {


    @Override
    public XgqWebLayuiTableVo assembleXgqWebLayuiTableVo(List<?> list, Long count) {
        XgqWebLayuiTableVo xgqWebLayuiTableVo = new XgqWebLayuiTableVo();
        xgqWebLayuiTableVo.setCode(Constants.LayUiTavleDataMode.LAY_CODE);
        xgqWebLayuiTableVo.setMsg(Constants.LayUiTavleDataMode.LAY_MSG);
        xgqWebLayuiTableVo.setCount(count);
        xgqWebLayuiTableVo.setData(list);
        return xgqWebLayuiTableVo;

    }
}
