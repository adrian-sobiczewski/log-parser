package com.wallethub.log_parser.log;

import com.wallethub.log_parser.vo.IPAddress;
import com.wallethub.log_parser.vo.RequestMethod;
import com.wallethub.log_parser.vo.UserAgent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogMapper implements RowMapper<LogEntity> {

    private static final String DATE = "date";
    private static final String ADDRESS = "address";
    private static final String METHOD = "method";
    private static final String STATUS = "status";
    private static final String AGENT = "agent";

    @Override
    public LogEntity mapRow(ResultSet rs, int i) throws SQLException {
        LogEntity e = new LogEntity();
        e.setDate(rs.getTimestamp(DATE).toLocalDateTime());
        e.setAddress(new IPAddress(rs.getString(ADDRESS)));
        e.setMethod(RequestMethod.of(rs.getString(METHOD)));
        e.setStatus(rs.getInt(STATUS));
        e.setAgent(new UserAgent(rs.getString(AGENT)));
        return e;
    }
}
