package com.careful.clinic.model.schedulemodels.table3;


import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE_DT;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="DISP_TABLE3_VIEW")
@NamedQueries({
        @NamedQuery(name = "findAllt3",query = "SELECT t FROM DISP_TABLE3 t order by t.lpuId")
})
public class DISP_TABLE3 implements Serializable {
    @Column(name = "LPUID")
    private String lpuId;

    @Column(name = "KOD_OTD")
    private String kodOtd;

    @Column(name = "USL")
    private String usl;

    @Column(name = "KAB")
    private String kab;

    @Column(name="TYPE_MO")
    private String typeMo;

    @Column(name="PRIM")
    private String prim;

    @Id
    @Column(name = "ID")
    private String Id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID",referencedColumnName = "ID")
    private List<DISP_TABLE_DT> dates;


    public List<DISP_TABLE_DT> getDates() {
        return dates;
    }

    public void setDates(List<DISP_TABLE_DT> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "DISP_TABLE3{" +
                "lpuId='" + lpuId + '\'' +
                ", kodOtd='" + kodOtd + '\'' +
                ", usl='" + usl + '\'' +
                ", kab='" + kab + '\'' +
                ", typeMo='" + typeMo + '\'' +
                ", priml='" + prim + '\'' +
                ", Id='" + Id + '\'' +
                ", dates=" + dates +
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

    public String getUsl() {
        return usl;
    }

    public void setUsl(String usl) {
        this.usl = usl;
    }

    public String getKab() {
        return kab;
    }

    public void setKab(String kab) {
        this.kab = kab;
    }

    public String getTypeMo() {
        return typeMo;
    }

    public void setTypeMo(String typeMo) {
        this.typeMo = typeMo;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String priml) {
        this.prim = priml;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
