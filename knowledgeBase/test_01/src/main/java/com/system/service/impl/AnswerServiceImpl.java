package com.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.mapper.AnswerMapper;
import com.system.pojo.Answer;
import com.system.service.AnswerService;
import org.springframework.stereotype.Service;

/**
 * @author DSX
 * @date 2024/3/18 20:14
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer>
        implements AnswerService {
}
