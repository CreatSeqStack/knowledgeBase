package com.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.pojo.Question;

/**
 * @author DSX
 * @date 2024/3/5 21:39
 */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 存储用户提出的问题
     *
     * @param question 问题
     * @return 返回修改的行数
     */
    int insertQuest(Question question);
}
