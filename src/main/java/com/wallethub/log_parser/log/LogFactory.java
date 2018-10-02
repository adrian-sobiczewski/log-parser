package com.wallethub.log_parser.log;

import com.wallethub.log_parser.vo.IPAddress;
import com.wallethub.log_parser.vo.RequestMethod;
import com.wallethub.log_parser.vo.UserAgent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogFactory {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public LogEntity create(String[] values) {
        LogEntity e = new LogEntity();
        e.setDate(date(values[0]));
        e.setAddress(address(values[1]));
        e.setMethod(method(values[2]));
        e.setStatus(status(values[3]));
        e.setAgent(agent(values[4]));
        return e;
    }

    private LocalDateTime date(String value) {
        return LocalDateTime.parse(value, DATE_FORMATTER);
    }

    private RequestMethod method(String value) {
        return RequestMethod.of(value);
    }

    private Integer status(String value) {
        return Integer.valueOf(value);
    }

    private IPAddress address(String value) {
        return new IPAddress(value);
    }

    private UserAgent agent(String value) {
        return new UserAgent(value.replaceAll("\"", ""));
    }

}
