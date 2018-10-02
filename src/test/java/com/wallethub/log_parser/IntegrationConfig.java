package com.wallethub.log_parser;

import com.wallethub.log_parser.blocked.BlockedFactory;
import com.wallethub.log_parser.blocked.BlockedService;
import com.wallethub.log_parser.cl.validator.CLValidator;
import com.wallethub.log_parser.file.FileReader;
import com.wallethub.log_parser.log.LogFactory;
import com.wallethub.log_parser.log.LogParser;
import com.wallethub.log_parser.log.LogPrinter;
import com.wallethub.log_parser.log.LogService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrationConfig {

    @Bean
    public ApplicationRunner applicationRunner(
            ApplicationArguments arguments,
            CLValidator validator,
            FileReader reader,
            LogParser parser,
            LogFactory factory,
            LogService service,
            LogPrinter printer,
            BlockedService blockedService,
            BlockedFactory blockedFactory,
            ApplicationShutdown shutdown
    ) {
        return new ApplicationRunner(
                arguments,
                validator,
                reader,
                parser,
                factory,
                service,
                printer,
                blockedService,
                blockedFactory,
                shutdown
        ) {
            @Override
            public void run() {
                // do nothing
            }
        };
    }

}
