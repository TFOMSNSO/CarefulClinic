package com.careful.clinic.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_JOB_WORK")
@NamedQueries({
        @NamedQuery(name = "findJobByDate",query = "select j from JobWork j where j.dateInsertTrunc = trunc(:inDate) order by j.dateInsert, j.id")
})
public class JobWork {
    @Id
    @Column
    Long id;

    @Column
    String state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss", timezone = "Asia/Novosibirsk")
    @Column(name = "D_INSERT")
    Date dateInsert;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name = "D_D")
    Date dateInsertTrunc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        this.dateInsert = dateInsert;
    }

    public Date getDateInsertTrunc() {
        return dateInsertTrunc;
    }

    public void setDateInsertTrunc(Date dateInsertTrunc) {
        this.dateInsertTrunc = dateInsertTrunc;
    }

    @Override
    public String toString() {
        return "JobWork{" +
                "Id=" + id +
                ", state='" + state + '\'' +
                ", dateInsert=" + dateInsert +
                ", dateInsertTrunc=" + dateInsertTrunc +
                '}';
    }
}
