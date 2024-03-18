package com.system.controller;

import com.alibaba.druid.util.StringUtils;
import com.system.pojo.User;
import com.system.service.UserService;
import com.system.utils.JwtHelper;
import com.system.utils.Result;
import com.system.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DSX
 * @date 2024/3/13 13:08
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录
     * 前端：
     * {
     *     "username":"张三", //用户名
     *     "userPwd":"123456"     //明文密码
     * }
     * 后端：
     * 1.成功
     * {
     *    "code":"200",         // 成功状态码
     *    "message":"success"   // 成功状态描述
     *    "data":{
     *     "token":"... ..." // 用户id的token
     *   }
     * }
     * 2.失败
     * {
     *    "code":"501",
     *    "message":"用户名有误"
     *    "data":{}
     * }
     * {
     *    "code":"503",
     *    "message":"密码有误"
     *    "data":{}
     * }
     */
    @PostMapping("login")
    public Result login(@RequestBody User user) {

        return userService.login(user);
    }

    /**
     * 检查账号是否可以注册
     * 前端：
     * username="张三"
     * 后端：
     * 1.成功
     * {
     *    "code":"200",
     *    "message":"success"
     *    "data":{}
     * }
     * 2.失败
     * {
     *    "code":"505",
     *    "message":"用户名已被占用"
     *    "data":{}
     * }
     */
    @GetMapping("checkUsername")
    public Result checkUsername(@RequestParam("username") String username) {

        return userService.checkUserName(username);
    }

    /**
     * 用户注册业务
     * 前端：
     * {
     *     "username":"zhang_san",
     *     "userPwd":"123456"
     * }
     * 后端：
     * 1.成功
     *{
     *    "code":"200",
     *    "message":"success"
     *    "data":{}
     * }
     * 2.失败
     * {
     *    "code":"505",
     *    "message":"userNameUsed"
     *    "data":{}
     * }
     * @param user 保存用户信息的对象
     * @return 返回注册结果
     */
    @PostMapping("register")
    public Result register(@RequestBody User user) {

        return userService.register(user);
    }

    /**
     * 检查token是否过期
     * @param token token字符串
     * @return 验证结果
     */
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token) {

        // token过期
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOT_LOGIN);
        }

        // token未过期
        return Result.ok(null);
    }

}
