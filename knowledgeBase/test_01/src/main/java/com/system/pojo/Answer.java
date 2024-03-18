package com.system.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author DSX
 * @date 2024/3/5 21:16
 */
@Data
public class Answer {
    // 问题编号
    @TableId
    private Integer questionId;

    // 对用户提出问题的回复
    private String answer;

    // 乐观锁
    @Version
    private Integer version;

    // 逻辑删除字段
    private Integer isDeleted;
}
