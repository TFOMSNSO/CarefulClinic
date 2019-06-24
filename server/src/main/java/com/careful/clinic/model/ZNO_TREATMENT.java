package com.careful.clinic.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the person_zno database table.
 *
 */
@Entity
@Table(name = "TOR_REGISTR_COLLECT")
@NamedQueries({
        @NamedQuery(name="treatmentZno.findbyid1",query = "SELECT z FROM ZNO_TREATMENT z " +
                "WHERE z.idRegistr = :p_id ORDER BY z.dateBegin")
})
public class ZNO_TREATMENT implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="TTYPE")
    private String ttype;

    @Column(name="ID_REGISTR")
    private String idRegistr;

    @Id
    @Column(name="ID_COLLECT")
    private String idCollect;

    @Column(name="TYPE_ACC")
    private String typeAcc;

    @Column(name="LINK_YEAR")
    private String linkYear;

    @Column(name="LPUID")
    private String lpuId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Column(name="DAT_BEG")
    private Date dateBegin;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Column(name="DAT_END")
    private Date dateEnd;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name="MES_KSG")
    private String mesKsg;

    @Column(name="DS_ONK")
    private String dsOnk;

    @Column(name="MKB")
    private String mkb;

    @Column(name="OT_PROFK")
    private String otProfk;

    @Column(name="LT_1")
    private String lt1;

    @Column(name="LT_2")
    private String lt2;

    @Column(name="LT_3")
    private String lt3;

    @Column(name="LT_4")
    private String lt4;

    @Column(name="LT_5")
    private String lt5;

    @Column(name="LT_6")
    private String lt6;


    public String getTtype() {
        return ttype;
    }
    public void setTtype(String ttype) {
        this.ttype = ttype;
    }
    public String getIdRegistr() {
        return idRegistr;
    }
    public void setIdRegistr(String idRegistr) {
        this.idRegistr = idRegistr;
    }
    public String getIdCollect() {
        return idCollect;
    }
    public void setIdCollect(String idCollect) {
        this.idCollect = idCollect;
    }
    public String getTypeAcc() {
        return typeAcc;
    }
    public void setTypeAcc(String typeAcc) {
        this.typeAcc = typeAcc;
    }
    public String getLinkYear() {
        return linkYear;
    }
    public void setLinkYear(String linkYear) {
        this.linkYear = linkYear;
    }
    public String getLpuId() {
        return lpuId;
    }
    public void setLpuId(String lpuId) {
        this.lpuId = lpuId;
    }
    public Date getDateBegin() {
        return dateBegin;
    }
    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }
    public Date getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    String getMesKsg() {
        return mesKsg;
    }
    public void setMesKsg(String mesKsg) {
        this.mesKsg = mesKsg;
    }
    public String getDsOnk() {
        return dsOnk;
    }
    public void setDsOnk(String dsOnk) {
        this.dsOnk = dsOnk;
    }
    public String getMkb() {
        return mkb;
    }
    public void setMkb(String mkb) {
        this.mkb = mkb;
    }
    public String getOtProfk() {
        return otProfk;
    }
    public void setOtProfk(String otProfk) {
        this.otProfk = otProfk;
    }
    public String getLt1() {
        return lt1;
    }
    public void setLt1(String lt1) {
        this.lt1 = lt1;
    }
    public String getLt2() {
        return lt2;
    }
    public void setLt2(String lt2) {
        this.lt2 = lt2;
    }
    public String getLt3() {
        return lt3;
    }
    public void setLt3(String lt3) {
        this.lt3 = lt3;
    }
    public String getLt4() {
        return lt4;
    }
    public void setLt4(String lt4) {
        this.lt4 = lt4;
    }
    public String getLt5() {
        return lt5;
    }
    public void setLt5(String lt5) {
        this.lt5 = lt5;
    }
    public String getLt6() {
        return lt6;
    }
    public void setLt6(String lt6) {
        this.lt6 = lt6;
    }

    public ZNO_TREATMENT(){}

    @Override
    public String toString() {
        return "ZNO_TREATMENT[ttype:"+ ttype +",idRegistr:" + idRegistr +  ",idCollect:" + idCollect +",typeAcc:"+ typeAcc + ",linkYear:" + linkYear
                + ",lpuId:" + lpuId + ",dateBegin:"+ dateBegin + ",dateEnd:" + dateEnd + ",mesKsg:" + mesKsg + ",dsOnk:" + dsOnk + ",mkb:"+ mkb
                + ",otProfk:" + otProfk + "\nlt1:" + lt1 + ",lt2:" + lt2+ ",lt3:" + lt3+ ",lt4:" + lt4+ ",lt5:" + lt5+ ",lt6:" + lt6 + "]";
    }
}