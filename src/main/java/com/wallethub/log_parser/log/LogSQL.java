package com.wallethub.log_parser.log;

public class LogSQL {

    public static String SAVE_BATCH =
            "INSERT INTO LOG (" +
                    "date, " +
                    "address, " +
                    "method, " +
                    "status, " +
                    "agent" +
                    ") VALUES (" +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?," +
                    "?" +
                    ")";

    public static String FIND_BY_PARAMETER
            = "SELECT address, COUNT(address) FROM LOG " +
            "WHERE date BETWEEN ? AND ? " +
            "GROUP BY address " +
            "HAVING COUNT(address) >= ?";
}
