package top.ptcc9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


@Configuration
public class ThreadPoolConfig {
    private static final Integer CORE_NUM = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 默认线程池线程池
     * @return Executor
     */
    @Bean
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数目
        executor.setCorePoolSize(CORE_NUM * 20);
        //指定最大线程数
        executor.setMaxPoolSize(CORE_NUM * 20 + 1);
        //队列中最大的数目
        executor.setQueueCapacity(Integer.MAX_VALUE);
        //线程名称前缀
        executor.setThreadNamePrefix("qr-order-bus-pool-");
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务 //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行 //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(60);
        //加载
        executor.initialize();
        return executor;
    }
}
