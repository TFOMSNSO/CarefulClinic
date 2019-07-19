package com.careful.clinic.model.schedulemodels.table1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(name = "findUpdateByDayst1", query = "SELECT t FROM DISP_TABLE1_UPDATE t where t.dateInsert > sysdate() - :days order by t.dateInsert desc")
})
public class DISP_TABLE1_UPDATE implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "LPUID")
    private String lpuId;

    @Column(name = "KOD_OTD")
    private String otdId;

    @Column(name = "ADRES")
    private String address;

    @Column(name = "TEL")
    private String phone;

    @Column(name = "TYPE_MO")
    private String typeMo;

    @Id
    @JsonIgnore
    @Column(name = "ID")
    private String Id;

    @Column(name = "PROF")
    private String prof;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", timezone = "Asia/Novosibirsk")
    @Column(name ="D_INSERT")
    private Date dateInsert;

    @Column(name = "PRIM")
    private String prim;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID",referencedColumnName = "ID")
    private List<DISP_TABLE1_DT_UPDATE> dates;

    public String getLpuId() {
        return lpuId;
    }

    public void setLpuId(String lpuId) {
        this.lpuId = lpuId;
    }

    public String getOtdId() {
        return otdId;
    }

    public void setOtdId(String otdId) {
        this.otdId = otdId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public Date getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        this.dateInsert = dateInsert;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public List<DISP_TABLE1_DT_UPDATE> getDates() {
        return dates;
    }

    public void setDates(List<DISP_TABLE1_DT_UPDATE> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "DISP_TABLE1_UPDATE{" +
                "lpuId='" + lpuId + '\'' +
                ", otdId='" + otdId + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", typeMo='" + typeMo + '\'' +
                ", Id='" + Id + '\'' +
                ", prof='" + prof + '\'' +
                ", dateInsert=" + dateInsert +
                ", prim='" + prim + '\'' +
                ", dates=" + dates +
                '}';
    }
}
