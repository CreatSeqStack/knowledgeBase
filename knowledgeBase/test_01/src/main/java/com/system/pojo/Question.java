package com.system.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
/**
 * @author DSX
 * @date 2024/3/5 21:15
 */
@Data
public class Question {

    // 问题编号
    @TableId
    private Integer questionId;

    // 用户id
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

    /**
     * 一个问题对应一个答案，使用答案对象装对应的信息
     */
    private Answer answer;

}
