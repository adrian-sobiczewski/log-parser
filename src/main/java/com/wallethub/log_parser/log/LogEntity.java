package com.wallethub.log_parser.log;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wallethub.log_parser.vo.IPAddress;
import com.wallethub.log_parser.vo.RequestMethod;
import com.wallethub.log_parser.vo.UserAgent;

import java.time.LocalDateTime;

public class LogEntity {

    private LocalDateTime date;
    private IPAddress address;
    private RequestMethod method;
    private Integer status;
    private UserAgent agent;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public IPAddress getAddress() {
        return address;
    }

    public void setAddress(IPAddress address) {
        this.address = address;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserAgent getAgent() {
        return agent;
    }

    public void setAgent(UserAgent agent) {
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return Objects.equal(date, logEntity.date) &&
                Objects.equal(address, logEntity.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date, address);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("date", date)
                .add("address", address)
                .add("method", method)
                .add("status", status)
                .add("agent", agent)
                .toString();
    }
}
