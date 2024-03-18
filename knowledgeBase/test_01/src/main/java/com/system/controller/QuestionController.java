package com.system.controller;

import com.system.pojo.Answer;
import com.system.pojo.Question;
import com.system.service.QuestionService;
import com.system.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DSX
 * @date 2024/3/5 21:38
 */
@RestController
@RequestMapping("question")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 保存前端发送的问题
     * 前端：
     * {
     *     "uid": 789,
     *     "question": "今天星期三"
     * }
     * token xxx.xxx.xxx
     * 后端：
     * 成功
     * {
     *     "code": 200,
     *     "message": "success",
     *     "data": null
     * }
     * 失败
     *  {
     *     "code": 500,
     *     "message": "insertError",
     *     "data": "数据插入失败"
     *  }
     * @param question 存储问题的对象
     * @return 返回执行结果的状态码
     */
    @PostMapping("ask")
    public Result insertQuest(@RequestBody Question question) {

        return questionService.insertQuest(question);
    }

    /**
     * 保存大模型发送来的答案
     * 大模型：
     *  JSON数据
     * 后端：
     * 成功
     * {
     *     "code": 200,
     *     "message": "success",
     *     "data": null
     * }
     * 失败
     * {
     *    "code": 500,
     *    "message": "insertError",
     *    "data": "数据插入失败"
     * }
     * @return 返回执行结果的状态码
     */
    @PostMapping("receiveAnswer")
    public Result receiveAnswer(@RequestBody Answer answer) {

        return questionService.receiveAnswer(answer);
    }

    /**
     * 根據用戶的uid查詢該用戶提出的全部问题
     * 前端：
     * param uid = 123
     * 后端：
     * 成功
     *  {
     *     "code": 200,
     *     "message": "success",
     *     "data": [
     *         {
     *             "questionId": 3,
     *             "uid": 123,
     *             "question": "今天星期三",
     *             "questionTime": "2024-03-18 16:27:44",
     *             "version": 1,
     *             "isDeleted": 0,
     *             "answer": null
     *         }
     *     ]
     * }
     * 失败
     *  {
     *     "code": 504,
     *     "message": "notLogin",
     *     "data": null
     * }
     * @param uid 用户id
     * @return 返回查询的结果
     */
    @GetMapping("queryAllQuestionByUid")
    public Result queryAllQuestionByUid(@RequestParam("uid") Integer uid) {

        return questionService.queryAllQuestionByUid(uid);
    }

    /**
     * 根据uid查询用户的历史记录，问题和答案
     * @param uid 用户id
     * @return 返回查询信息
     */
    @GetMapping("queryUserHistoryByUid")
    public Result queryUserHistoryByUid(@RequestParam("uid") Integer uid) {
        return questionService.queryUserHistoryByUid(uid);
    }
}
