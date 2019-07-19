package com.careful.clinic.model.schedulemodels.table4;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@NamedQueries({
        @NamedQuery(name="findDeleteHistoryT4",query = "SELECT t FROM DISP_TABLE4_HISTORY t where t.dateInsert > sysdate() - :days order by t.dateInsert desc")
})
public class DISP_TABLE4_HISTORY implements Serializable {
    @Column(name = "LPUID")
    private String lpuId;

    @Column(name = "ADRES")
    private String address;

    @Column(name = "DATE_BEGIN")
    private String dateBegin;

    @Column(name = "DATE_END")
    private String dateEnd;

    @Column(name = "TIME_BEGIN")
    private String timeBegin;

    @Column(name = "TIME_END")
    private String timeEnd;

    @Column(name = "TYPE_MO")
    private String typeMo;

    @Id
    @JsonIgnore
    @Column(name = "ID")
    private String Id;

    @Column(name = "PRIM")
    private String prim;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Column(name = "D_INSERT")
    private Date dateInsert;

    public Date getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        this.dateInsert = dateInsert;
    }

    public String getLpuId() {
        return lpuId;
    }

    public void setLpuId(String lpuId) {
        this.lpuId = lpuId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTypeMo() {
        return typeMo;
    }

    public void setTypeMo(String typeMo) {
        this.typeMo = typeMo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    @Override
    public String toString() {
        return "DISP_TABLE4{" +
                "lpuId='" + lpuId + '\'' +
                ", address='" + address + '\'' +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", timeBegin='" + timeBegin + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", typeMo='" + typeMo + '\'' +
                ", Id='" + Id + '\'' +
                ", prim='" + prim + '\'' +
                '}';
    }
}
