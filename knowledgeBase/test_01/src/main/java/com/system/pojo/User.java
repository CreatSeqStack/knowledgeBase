package com.system.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author DSX
 * @date 2024/3/5 21:44
 */
@Data
public class User {

    // 用户ID
    @TableId
    private Integer uid;

    // 用户名
    private String username;

    // 用户密码
    private String userPwd;

    // 乐观锁
    @Version
    private Integer version;

    // 逻辑删除字段
    private Integer isDeleted;

}
