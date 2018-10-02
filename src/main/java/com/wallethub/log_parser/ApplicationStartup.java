package com.wallethub.log_parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final ApplicationRunner runner;

    @Autowired
    public ApplicationStartup(ApplicationRunner runner) {
        this.runner = runner;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        runner.run();
    }

}
