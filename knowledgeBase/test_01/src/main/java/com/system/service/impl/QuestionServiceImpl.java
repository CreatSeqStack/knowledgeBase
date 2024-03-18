package com.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.AnswerMapper;
import com.system.mapper.QuestionMapper;
import com.system.pojo.Answer;
import com.system.pojo.Question;
import com.system.service.QuestionService;
import com.system.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.system.utils.ResultCodeEnum.INSERT_ERROR;

/**
 * @author DSX
 * @date 2024/3/5 21:36
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 保存用户提出的问题
     * @param question 接收问题的对象
     * @return 返回执行结果的状态码
     */
    @Override
    public Result insertQuest(Question question) {
        int rows = questionMapper.insertQuest(question);

        if (rows < 1) {
            return Result.build("数据插入失败" ,INSERT_ERROR);
        }

        return Result.ok(null);
    }

    /**
     * 根据uid查询用户提出的全部问题
     * @return 返回该用户提出的全部问题
     */
    @Override
    public Result queryAllQuestionByUid(Integer uid) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(Question.class, item -> !"answer".equals(item.getColumn()))
                .eq(Question::getUid, uid);
        List<Question> questions = questionMapper.selectList(lambdaQueryWrapper);

        return Result.ok(questions);
    }

    /**
     * 根据uid查询用户的历史记录，问题和答案
     * @param uid 用户id
     * @return 返回查询信息
     */
    @Override
    public Result queryUserHistoryByUid(Integer uid) {
        List<Question> result = questionMapper.queryUserHistoryByUid(uid);
        return Result.ok(result);
    }

    /**
     * 接受大模型传来的答案
     * @param answer 答案
     * @return 返回执行结果
     */
    @Override
    public Result receiveAnswer(Answer answer) {
        int rows = answerMapper.insert(answer);

        if (rows < 1) {
            return Result.build("数据插入失败", INSERT_ERROR);
        }

        return Result.ok(null);
    }


}
