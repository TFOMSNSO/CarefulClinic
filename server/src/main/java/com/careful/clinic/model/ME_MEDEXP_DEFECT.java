package com.careful.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class ME_MEDEXP_DEFECT implements Serializable {
    @Id
    @Column(name="ID")
    private String id;

    @Column(name="MEDEXPR_ID")
    private String medexprId;

    @OneToOne
    @JoinColumn(name = "DEFECT_ID")
    private ME_SPR_DEFECTS defectInfo;




    public String getMedexprId() {
        return medexprId;
    }

    public void setMedexprId(String medexprId) {
        this.medexprId = medexprId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ME_SPR_DEFECTS getDefectInfo() {
        return defectInfo;
    }

    public void setDefectInfo(ME_SPR_DEFECTS defectInfo) {
        this.defectInfo = defectInfo;
    }

    public ME_MEDEXP_DEFECT(){}

}
