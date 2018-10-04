package com.xgq.service;

import com.xgq.common.ServerResponse;
import com.xgq.entity.UserInfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HeJD
 * @since 2018-09-17
 */
public interface IUserInfoService extends IService<UserInfo> {

    List<UserInfo> selectAll();

    ServerResponse updateUserInfo(UserInfo userInfo);
}
