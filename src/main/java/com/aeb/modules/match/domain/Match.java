package com.aeb.modules.match.domain;

import java.sql.Date;

import com.aeb.modules.team.domain.Team;

public class Match {

    private Team local;
    private Team visitor;
    private Integer localGoals;
    private Integer visitorGoals;
    private Date date;
    
    public Team getLocal() {
        return local;
    }

    public Team getVisitor() {
        return visitor;
    }

    public Integer getLocalGoals() {
        return localGoals;
    }

    public Integer getVisitorGoals() {
        return visitorGoals;
    }

    public Date getDate() {
        return date;
    }

    public void setLocal(Team local) {
        this.local = local;
    }

    public void setVisitor(Team visitor) {
        this.visitor = visitor;
    }

    public void setLocalGoals(Integer localGoals) {
        this.localGoals = localGoals;
    }

    public void setVisitorGoals(Integer visitorGoals) {
        this.visitorGoals = visitorGoals;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Match(Team local, Team visitor, Integer localGoals, Integer visitorGoals, Date date) {
        this.local = local;
        this.visitor = visitor;
        this.localGoals = localGoals;
        this.visitorGoals = visitorGoals;
        this.date = date;
    }

}
