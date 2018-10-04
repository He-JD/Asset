package com.xgq.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xgq.common.ServerResponse;
import com.xgq.dao.UserInfoDao;
import com.xgq.entity.UserInfo;
import com.xgq.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HeJD
 * @since 2018-09-17
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> selectAll() {
        return  userInfoDao.selectList(null);
    }

    @Override
    public ServerResponse updateUserInfo(UserInfo userInfo) {

        Integer result= userInfoDao.updateAllColumnById(userInfo);
        if (result>0){
            return ServerResponse.createBySuccess(userInfoDao.selectById(userInfo.getId()));
        }
         return ServerResponse.createByErrorMessage("修改更新用户数据失败");
    }
}
