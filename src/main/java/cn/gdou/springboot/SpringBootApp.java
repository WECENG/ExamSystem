package cn.gdou.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

@ComponentScan("cn.gdou")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class SpringBootApp extends SpringBootServletInitializer implements WebApplicationInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){

        return application.sources(SpringBootApp.class);
    }
    public static void main(String[] args){
        SpringApplication.run(SpringBootApp.class);
    }
}
