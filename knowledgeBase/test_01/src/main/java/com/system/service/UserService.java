package com.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.pojo.User;
import com.system.utils.Result;

/**
 * @author DSX
 * @date 2024/3/13 13:10
 */

public interface UserService extends IService<User> {
    /**
     * 登录业务
     * @param user 接受用户信息的对象
     * @return 返回token
     */
    Result login(User user);

    /**
     * 检查用户名是否被注册
     * @param username 要进行检验的用户名
     * @return 验证结果
     */
    Result checkUserName(String username);

    /**
     * 用户注册业务
     * @param user 保存用户信息的对象
     * @return 返回注册结果
     */
    Result register(User user);
}
