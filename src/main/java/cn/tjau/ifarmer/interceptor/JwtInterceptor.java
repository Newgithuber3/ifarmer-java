package cn.tjau.ifarmer.interceptor;

import cn.tjau.ifarmer.annotation.PassToken;
import cn.tjau.ifarmer.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        System.out.println(2);
        Map<String, Object> map = new HashMap<>();
        // 执行认证
        try {
            if (token == null || Objects.equals(token, "")) {
                map.put("success",false);
                map.put("message","用户未登录");
                String json = new ObjectMapper().writeValueAsString(map);
                httpServletResponse.setContentType("application/json; charset=utf-8");
                httpServletResponse.getWriter().println(json);
                return false;
            }
            // 验证 token
            JwtUtils.checkSign(token);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","验证身份失败");
            String json = new ObjectMapper().writeValueAsString(map);
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().println(json);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}