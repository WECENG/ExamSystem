package cn.gdou.web.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/");
        registry.addResourceHandler("/exam/**")
                .addResourceLocations("/");
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver view=
                new InternalResourceViewResolver();
        view.setContentType("utf-8");
        view.setSuffix(".jsp");
        view.setPrefix("/WEB-INF/views/");
        return view;

    }
}
