package com.careful.clinic.model.schedulemodels.table2;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllt2",query = "SELECT t FROM DISP_TABLE2 t order by t.lpuId")
})
public class DISP_TABLE2 implements Serializable {
    @Column(name = "LPUID")
    private String lpuId;

    @Column(name = "FIO")
    private String fio;

    @Column(name = "DOL")
    private String dol;

    @Column(name = "TEL")
    private String phone;

    @Id
    @Column(name = "ID")
    private String Id;

    @Column(name = "PRIM")
    private String prim;

    public String getLpuId() {
        return lpuId;
    }

    public void setLpuId(String lpuId) {
        this.lpuId = lpuId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDol() {
        return dol;
    }

    public void setDol(String dol) {
        this.dol = dol;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "DISP_TABLE2{" +
                "lpuId='" + lpuId + '\'' +
                ", fio='" + fio + '\'' +
                ", dol='" + dol + '\'' +
                ", phone='" + phone + '\'' +
                ", Id='" + Id + '\'' +
                ", prim='" + prim + '\'' +
                '}';
    }
}
