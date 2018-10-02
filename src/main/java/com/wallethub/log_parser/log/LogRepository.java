package com.wallethub.log_parser.log;

import com.wallethub.log_parser.parameter.Parameter;
import com.wallethub.log_parser.vo.IPAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LogRepository {

    public static final LogMapper MAPPER = new LogMapper();
    private final JdbcTemplate jdbc;

    @Autowired
    public LogRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(final List<LogEntity> logs) {
        this.jdbc.batchUpdate(LogSQL.SAVE_BATCH, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                LogEntity e = logs.get(i);
                ps.setTimestamp(1, Timestamp.valueOf(e.getDate()));
                ps.setString(2, e.getAddress().getValue());
                ps.setObject(3, e.getMethod().name());
                ps.setInt(4, e.getStatus());
                ps.setString(5, e.getAgent().getValue());
            }

            @Override
            public int getBatchSize() {
                return logs.size();
            }
        });
    }

    public List<IPAddress> findByIPsParameter(Parameter parameter) {
        LocalDateTime start = parameter.getStart();
        LocalDateTime end = start.plus(parameter.getDuration());
        Integer threshold = parameter.getThreshold();
        return jdbc.query(LogSQL.FIND_BY_PARAMETER, (rs, i) -> new IPAddress(rs.getString(1)), start, end, threshold);
    }
}
