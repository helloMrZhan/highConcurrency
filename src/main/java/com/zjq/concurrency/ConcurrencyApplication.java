package com.zjq.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.FilterRegistration;

/**
 * 并发
 * @author zjq
 */
@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    /**
     * 自定义过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new HttpFilter());
        filterRegistrationBean.addUrlPatterns("/threadLocal/*");
        return filterRegistrationBean;
    }

    /**
     * 定义自定义拦截器原先需要继承WebMvcConfigurerAdapter
     * SpringBoot2.0后WebMvcConfigurerAdapter被定义成过时了，推荐使用继承WebMvcConfigurationSupport
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
