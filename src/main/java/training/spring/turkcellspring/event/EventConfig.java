package training.spring.turkcellspring.event;

import org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class EventConfig {

    @Bean
    public Executor threadPoolA() {
        return Executors.newFixedThreadPool(4);
    }

    @Bean
    public Executor threadPoolB() {
        return new SimpleAsyncTaskExecutorBuilder().concurrencyLimit(4)
                                                   .threadNamePrefix("my-th-")
                                                   .build();
    }

    @Bean("defaultThreadPool")
    public TaskExecutor threadPoolC() {
        ThreadPoolTaskExecutor thPool = new ThreadPoolTaskExecutor();
        thPool.setCorePoolSize(4);
        thPool.setMaxPoolSize(10);
        thPool.setQueueCapacity(500);
        thPool.setThreadNamePrefix("s-th-");
        thPool.initialize();
        return thPool;
    }

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster multicasterLoc = new SimpleApplicationEventMulticaster();
        multicasterLoc.setTaskExecutor(threadPoolC());
        return multicasterLoc;
    }
}
