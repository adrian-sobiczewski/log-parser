package com.wallethub.log_parser.log;

import com.wallethub.log_parser.batch.Batcher;
import com.wallethub.log_parser.parameter.Parameter;
import com.wallethub.log_parser.vo.IPAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogService {

    private final LogRepository repository;
    private final Batcher<LogEntity> batcher;

    @Autowired
    public LogService(LogRepository repository, @Value("${log-parser.batch.size}") int size) {
        this.repository = repository;
        this.batcher = new Batcher<>(size, this::save);
    }

    public void save(LogEntity log) {
        batcher.add(log);
    }

    @Transactional
    public void save(List<LogEntity> logs) {
        repository.save(logs);
    }

    @Transactional
    public List<IPAddress> getBy(Parameter parameter) {
        return repository.findByIPsParameter(parameter);
    }

    public void forceBatch() {
        batcher.batch();
    }
}
