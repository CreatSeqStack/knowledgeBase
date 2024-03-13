package com.system.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.UserMapper;
import com.system.pojo.User;
import com.system.service.UserService;
import com.system.utils.JwtHelper;
import com.system.utils.MD5Util;
import com.system.utils.Result;
import com.system.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.system.utils.ResultCodeEnum.INSERT_ERROR;

/**
 * @author DSX
 * @date 2024/3/13 13:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录业务
     *   1.根据账号，查询用户对象
     *   2.如果用户对象为null，查询失败返回 501
     *   3.对比密码  失败返回 503
     *   4.根据用户id生成一个token，token 作为 Result 返回
     * @param user 接受用户信息的对象
     * @return 返回token
     */
    @Override
    public Result login(User user) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);

        // 判断数据库中是否有该用户
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        // 判断密码是否正确
        if (!StringUtils.isEmpty(user.getUserPwd())
            && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())) {

            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map<String, String> map = new HashMap<>();
            map.put("token", token);

            return Result.ok(map);
        }

        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    /**
     * 检查账号是否可以注册
     *   1.根据账号进行count查询
     *   2.count == 0 账号可以注册
     *   3.count > 0 账号已被注册
     * @param username 要检验的用户名
     * @return 验证结果
     */
    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);

        // 查询该用户名是否在数据库中
        Long count = userMapper.selectCount(lambdaQueryWrapper);

        if (count == 0) {
            return Result.ok(null);
        }

        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 注册业务
     * 1.检查账号是否已经被注册
     * 2.密码加密处理
     * 3.保存账号密码
     * 4.返回结果
     * @param user 保存用户信息的对象
     * @return 返回注册结果
     */
    @Override
    public Result register(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        // 查询该用户名是否在数据库中
        Long count = userMapper.selectCount(lambdaQueryWrapper);

        if (count > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        // 进行密码加密并赋值给改对象
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        int insert = userMapper.insert(user);

        if (insert < 1) {
            return Result.build("数据插入失败" ,INSERT_ERROR);
        }

        return Result.ok(null);
    }


}
