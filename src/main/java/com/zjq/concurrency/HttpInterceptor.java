package com.zjq.concurrency;

import com.zjq.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>自定义拦截器</p>
 *
 * @Author zjq
 * @Date 2021/12/7
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    /**
     * 拦截处理程序的执行。在 HandlerMapping 确定合适的处理程序对象之后，在 HandlerAdapter 调用处理程序之前调用。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle执行。。。");
        return true;
    }

    /**
     * 请求处理完成后（渲染视图后）的回调。将在处理程序执行的任何结果上调用，从而允许进行适当的资源清理。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
        log.info("afterCompletion执行。。。");
        return;
    }
}
