package com.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.pojo.Question;
import com.system.utils.Result;

/**
 * @author DSX
 * @date 2024/3/5 21:36
 */
public interface QuestionService extends IService<Question> {

    /**
     * 存储用户提出的问题
     * @param question 接收问题的对象
     */
    Result insertQuest(Question question);

    /**
     * 根据uid查询用户提出的所有问题
     * @param uid 用户Id
     */
    Result queryAllQuestionByUid(Integer uid);
}
