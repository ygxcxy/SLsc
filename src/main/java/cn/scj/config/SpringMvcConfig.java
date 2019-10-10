package cn.scj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * @author 邱道长
 * @since 2019/8/7
 */
@Configuration
@ComponentScan(basePackages = {"cn.scj.controller"})
public class SpringMvcConfig {

    // 注册视图解析器
    @Bean
    public InternalResourceViewResolver resourceViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    // 注册文件上传解析器
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return  commonsMultipartResolver;
    }

//    // 注册一个异常解析器
//    @Bean
//    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
//        SimpleMappingExceptionResolver sr = new SimpleMappingExceptionResolver();
//        Properties mappings = new Properties();
//        // 404 视图定义
//        mappings.setProperty(NoHandlerFoundException.class.getName(),"error/404");
//        sr.setExceptionMappings(mappings);
//        //定义默认错误视图
//        sr.setDefaultErrorView("error/error");
//        return sr;
//    }

}
