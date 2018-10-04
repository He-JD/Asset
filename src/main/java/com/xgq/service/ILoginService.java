package com.xgq.service;

import com.xgq.common.ServerResponse;
import org.springframework.ui.Model;

/**
 * @Auther: HeJD
 * @Date: 2018/9/29 13:53
 * @Description:
 */
public interface ILoginService {


    ServerResponse login(String loginName, String password, String rememberMe);


    String mainPage(Model model);
}
