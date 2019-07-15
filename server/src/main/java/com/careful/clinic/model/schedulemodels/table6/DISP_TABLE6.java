package com.careful.clinic.model.schedulemodels.table6;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "DISP_TABLE6_VIEW")
@NamedQueries({
        @NamedQuery(name = "findAllt6",query = "SELECT t FROM DISP_TABLE6 t order by t.lpuId")
})
public class DISP_TABLE6 implements Serializable {
    @Column(name = "LPUID")
    private String lpuId;

    @Column(name = "KOD_OTD")
    private String kodOtd;

    @Column(name = "KAB")
    private String kab;

    @Column(name = "DATA")
    private String date;

    @Column(name = "TIME_BEGIN")
    private String timeBegin;

    @Column(name = "TIME_END")
    private String timeEnd;

    @Column(name = "TYPE_MO")
    private String typeMo;

    @Id
    @Column(name = "ID")
    private String Id;

    @Column(name = "PRIM")
    private String prim;

    @Override
    public String toString() {
        return "DISP_TABLE6{" +
                "lpuId='" + lpuId + '\'' +
                ", kodOtd='" + kodOtd + '\'' +
                ", kab='" + kab + '\'' +
                ", date='" + date + '\'' +
                ", timeBegin='" + timeBegin + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", typeMo='" + typeMo + '\'' +
                ", Id='" + Id + '\'' +
                ", prim='" + prim + '\'' +
                '}';
    }

    public String getLpuId() {
        return lpuId;
    }

    public void setLpuId(String lpuId) {
        this.lpuId = lpuId;
    }

    public String getKodOtd() {
        return kodOtd;
    }

    public void setKodOtd(String kodOtd) {
        this.kodOtd = kodOtd;
    }

    public String getKab() {
        return kab;
    }

    public void setKab(String kab) {
        this.kab = kab;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
