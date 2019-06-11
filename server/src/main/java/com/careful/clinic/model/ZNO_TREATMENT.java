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
@Table(name = "TOP_REGIST_COLLECT")
public class ZNO_TREATMENT implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="TTYPE")
    private String ttype;

    @Column(name="ID_REGISTR")
    private String idRegistr;

    @Column(name="ID_COLLECT")
    private String idCollect;

    @Column(name="TYPE_ACC")
    private String typeAcc;

    @Column(name="LINK_YEAR")
    private String linkYear;

    @Column(name="LPUID")
    private String lpuId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    //@Temporal(TemporalType.DATE)
    @Column(name="DAT_BEG")
    private Date dateBegin;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    //@Temporal(TemporalType.DATE)
    @Column(name="DAT_END")
    private Date dateEnd;

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




    @Override
    public String toString() {
        return "ZNO_TREATMENT[ttype:"+ ttype +",idRegistr:" + idRegistr +  ",idCollect:" + idCollect +",typeAcc:"+ typeAcc + ",linkYear:" + linkYear
                + ",lpuId:" + lpuId + ",dateBegin:"+ dateBegin + ",dateEnd:" + dateEnd + ",mesKsg:" + mesKsg + ",dsOnk:" + dsOnk + ",mkb:"+ mkb
                + ",otProfk" + otProfk + "\nlt1:" + lt1 + ",lt2:" + lt2+ ",lt3:" + lt3+ ",lt4:" + lt4+ ",lt5:" + lt5+ ",lt6:" + lt6 + "]";
    }
}