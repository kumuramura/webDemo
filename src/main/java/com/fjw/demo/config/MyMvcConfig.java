package com.fjw.demo.config;

import com.fjw.demo.component.LoginHandleInterceptor;
import com.fjw.demo.component.MyLocalResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurer来扩展springMVC的功能
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /fjw 也能来success
        registry.addViewController("/fjw").setViewName("success1");
    }



    //所有的WebMvcConfigurer组件都会一起起作用
    @Bean//将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurer()
    {
        WebMvcConfigurer Wconfigurer= new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/index").setViewName("login");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");

                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源； *.js  *.css
                //spring boot已经做好了静态资源映射

                registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/index","/index.html","/login","/login.html","/user/login",
                        "/asserts/css/**","/asserts/img/**","/asserts/js/**");



            }
        };


        return Wconfigurer;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
}
