package com.wallethub.log_parser;

import com.wallethub.log_parser.blocked.BlockedFactory;
import com.wallethub.log_parser.blocked.BlockedService;
import com.wallethub.log_parser.cl.validator.CLValidator;
import com.wallethub.log_parser.file.FileReader;
import com.wallethub.log_parser.log.LogFactory;
import com.wallethub.log_parser.log.LogParser;
import com.wallethub.log_parser.log.LogPrinter;
import com.wallethub.log_parser.log.LogService;
import com.wallethub.log_parser.parameter.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import static com.wallethub.log_parser.cl.CLArgument.*;

@Component
public class ApplicationRunner {

    private static Logger LOG = LoggerFactory.getLogger(ApplicationRunner.class);

    private final ApplicationArguments arguments;
    private final CLValidator validator;
    private final FileReader reader;
    private final LogParser parser;
    private final LogFactory factory;
    private final LogService service;
    private final LogPrinter printer;
    private final BlockedService blockedService;
    private final BlockedFactory blockedFactory;
    private ApplicationShutdown shutdown;

    @Value("${log-parser.file-path}")
    private String path;

    @Autowired
    public ApplicationRunner(
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

        this.arguments = arguments;
        this.validator = validator;
        this.reader = reader;
        this.parser = parser;
        this.factory = factory;
        this.service = service;
        this.printer = printer;
        this.blockedService = blockedService;
        this.blockedFactory = blockedFactory;
        this.shutdown = shutdown;
    }

    public void run() {
        validator.validate(arguments);
        parser.parse(reader.read(path(arguments)), values -> service.save(factory.create(values)));
        Parameter parameter = Parameter.of(
                START_DATE.extract(arguments),
                DURATION.extract(arguments),
                THRESHOLD.extract(arguments)
        );
        service.getBy(parameter).forEach(address -> {
            blockedService.save(blockedFactory.create(address));
            printer.print(address);
        });
        shutdown.shutdown();
    }

    private String path(ApplicationArguments arguments) {
        String p = ACCESS_LOG.extract(arguments);
        return p == null ? path : p;
    }
}
