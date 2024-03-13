package com.system.utils;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 * @author DSX
 */
@Getter
public enum ResultCodeEnum {

    // 操作成功
    SUCCESS(200,"success"),

    // 用户名错误
    USERNAME_ERROR(501,"usernameError"),

    // 密码错误
    PASSWORD_ERROR(503,"passwordError"),

    // 未登录
    NOT_LOGIN(504,"notLogin"),

    // 用户名已被占用
    USERNAME_USED(505,"userNameUsed"),

    // 数据库数据插入失败
    INSERT_ERROR(500, "insertError"),

    // 数据库数据查询失败
    QUERY_ERROR(506, "queryError");

    private final Integer code;
    private final String message;
    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
