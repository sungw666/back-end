package top.ptcc9.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.ptcc9.annotations.LoginRequired;
import top.ptcc9.exceptions.InvalidTokenException;
import top.ptcc9.utils.CommonUtil;
import top.ptcc9.utils.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Configuration
public class TokenInterceptor extends WebMvcConfigurationSupport {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor handlerInterceptor = new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (!(handler instanceof HandlerMethod)) {
                    return true;
                }
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                if (method.isAnnotationPresent(LoginRequired.class)) {
                    String token = request.getHeader("token");
                    if (CommonUtil.isEmpty(token) || !jwtUtil.verity(token)) {
                        throw new InvalidTokenException();
                    }
                }
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            }
        };
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }

}
