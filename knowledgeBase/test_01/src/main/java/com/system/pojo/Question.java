package com.system.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @author DSX
 * @date 2024/3/5 21:15
 */
@Data
public class Question {

    // 用户id
    @TableId
    private Integer uid;

    // 用户提出的问题
    private String question;

    // 提出问题的时间
    private String questionTime;

    // 乐观锁
    @Version
    private Integer version;

    // 逻辑删除字段
    private Integer isDeleted;

}
