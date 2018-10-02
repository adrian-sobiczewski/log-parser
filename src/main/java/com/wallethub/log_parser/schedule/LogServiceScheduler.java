package com.wallethub.log_parser.schedule;

import com.wallethub.log_parser.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogServiceScheduler {

    private final LogService service;

    @Autowired
    public LogServiceScheduler(LogService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 1000)
    public void forceBatch() {
        service.forceBatch();
    }

}
