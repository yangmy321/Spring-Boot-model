package top.test.boot.fi.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author 34746
 */
@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log. info("method: {}, uri:{}, parameterMap: {}, currentTime: {}", method, uri, parameterMap, LocalDateTime.now());
        return true;
    }
}
