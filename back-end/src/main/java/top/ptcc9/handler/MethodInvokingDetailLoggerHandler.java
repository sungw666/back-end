package top.ptcc9.handler;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.ptcc9.utils.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Component
@Aspect
@Order(1)
public class MethodInvokingDetailLoggerHandler {
    private static final Log logger = LogFactory.get(MethodInvokingDetailLoggerHandler.class);

    @Around(value = "execution(* top.ptcc9.controller.*.*(..))")
    public Object logger(ProceedingJoinPoint pjp) throws Throwable {
        /* 是否需要日志打印 */
        boolean isLoggerDetailPrint = true;

        /* 不需要 */
        if (!isLoggerDetailPrint) {
            return pjp.proceed();
        }

        final HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        /* 获取参数名列表 */
        String[] paramNames = ((CodeSignature) pjp.getSignature()).getParameterNames();
        Object[] paramValues = pjp.getArgs();
        String requestPath = request.getRequestURI();

        StringBuilder sb = new StringBuilder();

        sb.append("\n请求地址： ").append(requestPath).append("\n");
        sb.append("参数列表： ").append(Arrays.toString(paramNames)).append("\n");
        sb.append("参数值： ").append(JSONUtil.toJsonPrettyStr(paramValues));

        long start = CommonUtil.getSimpleDateTime();
        Object obj = null;
        try {
            obj = pjp.proceed();
        }catch (RuntimeException e) {
            logger.error(String.valueOf(sb));
            throw e;
        }
        long end = CommonUtil.getSimpleDateTime();

        String response = JSONUtil.toJsonPrettyStr(obj);

        sb.append("\n响应结果： ").append(response).append("\n");
        sb.append("响应耗时： ").append(end - start).append("ms");
        logger.info(String.valueOf(sb));
        return obj;
    }
}
