package com.wallethub.log_parser.blocked;

import com.wallethub.log_parser.vo.IPAddress;

public class BlockedEntity {

    private int id;
    private IPAddress address;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IPAddress getAddress() {
        return address;
    }

    public void setAddress(IPAddress address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
