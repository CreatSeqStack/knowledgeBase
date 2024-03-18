package com.system.intercepors;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.utils.JwtHelper;
import com.system.utils.Result;
import com.system.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author DSX
 * @date 2024/3/14 16:49
 * 检验是否登录的拦截器，方法：token是否有效
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 从请求中获取token
        String token = request.getHeader("token");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int userId = jwtHelper.getUserId(token).intValue();

        // 检验token有效性并且判断token是否属于该用户
        if (!StringUtils.isEmpty(token) && !jwtHelper.isExpiration(token) && uid == userId) {
            // token没有过期，放行
            return true;
        }

        // token过期或token与当前用户不符合当做未登录处理
        Result result = Result.build(null, ResultCodeEnum.NOT_LOGIN);

        // 将result转换为json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);

        return false;
    }
}
