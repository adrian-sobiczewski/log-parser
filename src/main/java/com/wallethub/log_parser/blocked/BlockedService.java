package com.wallethub.log_parser.blocked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockedService {

    private final BlockedRepository repository;

    @Autowired
    public BlockedService(BlockedRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(BlockedEntity entity) {
        repository.save(entity);
    }
}
