package com.wallethub.log_parser.blocked;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlockedRepository {

    private final JdbcTemplate jdbc;

    public BlockedRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(BlockedEntity entity) {
        jdbc.update(BlockedSql.SAVE, entity.getAddress().getValue(), entity.getComment());
    }
}
