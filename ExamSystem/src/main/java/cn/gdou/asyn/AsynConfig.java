package cn.gdou.asyn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class AsynConfig {
    @Bean
    public TaskExecutor taskExecutor() {
        /*
         * ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
         * taskExecutor.setMaxPoolSize(4); taskExecutor.afterPropertiesSet();
         */

        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(-1);
        return taskExecutor;
    }

}
