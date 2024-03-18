package com.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.pojo.Question;

import java.util.List;

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

    /**
     * 根据uid查询用户的历史记录（问题和答案）
     * @param uid 用户id
     * @return 返回查询信息
     */
    List<Question> queryUserHistoryByUid(Integer uid);
}
