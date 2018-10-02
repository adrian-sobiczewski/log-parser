package com.wallethub.log_parser.blocked;

public class BlockedSql {

    public static final String SAVE =
            "INSERT INTO BLOCKED (" +
                    "address, " +
                    "comment" +
                    ") VALUES (" +
                    "?, " +
                    "?" +
                    ")";
}
