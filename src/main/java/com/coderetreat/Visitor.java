package com.coderetreat;

import java.util.Date;

public class Visitor {
    private String id;
    private String ip;
    private Date visitDate;

    public Visitor(final String id, final String ip, final Date visitDate) {
        this.id = id;
        this.ip = ip;
        this.visitDate = visitDate;
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public Date getVisitDate() {
        return visitDate;
    }
}
