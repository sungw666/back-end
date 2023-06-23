package top.ptcc9.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartingTask implements ApplicationRunner {
    private static final Log log = LogFactory.get(StartingTask.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
