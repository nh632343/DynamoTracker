package com.jinke.dynamodb.test.timer;

import com.jinke.dynamodb.test.TestWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ThreadPoolTimer {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");

    @Scheduled(cron = "0/2 * * * * ?")
    public void dolog() {
        TestWorker testWorker = TestWorker.singleTon;
        if (testWorker == null) return;
        log.info(format.format(new Date()) + "  " + testWorker.getThreadPoolMsg());
    }
}
