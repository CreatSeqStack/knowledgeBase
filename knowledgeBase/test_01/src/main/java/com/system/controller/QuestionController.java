package com.system.controller;

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

    @PostMapping("ask")
    public Result insertQuest(@RequestBody Question question) {

        return questionService.insertQuest(question);
    }

    @GetMapping("queryAllQuestionByUid")
    public Result queryAllQuestionByUid(@RequestParam("uid") Integer uid) {

        return questionService.queryAllQuestionByUid(uid);
    }
}
