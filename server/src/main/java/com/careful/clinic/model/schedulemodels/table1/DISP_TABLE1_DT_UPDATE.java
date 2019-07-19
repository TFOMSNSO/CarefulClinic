package com.careful.clinic.model.schedulemodels.table1;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DISP_TABLE_DT_UPDATE_VIEW")
public class DISP_TABLE1_DT_UPDATE {
    @JsonIgnore
    @javax.persistence.Id
    @Column(name = "NOMER")
    private String nomer;

    @Column(name = "ID")
    private String Id;

    @Column(name = "DW")
    private String dw;

    @Column(name = "DY")
    private String dy;

    @Column(name = "DT_BEGIN")
    private String timeBegin;

    @Column(name = "DT_END")
    private String timeEnd;


    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String dateBegin) {
        this.timeBegin = dateBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String dateEnd) {
        this.timeEnd = dateEnd;
    }


    @Override
    public String toString() {
        return "DISP_TABLE_DT_UPDATE{" +
                "nomer='" + nomer + '\'' +
                ", Id='" + Id + '\'' +
                ", dw='" + dw + '\'' +
                ", dy='" + dy + '\'' +
                ", dateBegin='" + timeBegin + '\'' +
                ", dateEnd='" + timeEnd + '\'' +
                '}';
    }
}
