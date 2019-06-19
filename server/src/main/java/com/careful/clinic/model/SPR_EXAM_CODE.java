package com.careful.clinic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQueries({
        @NamedQuery(name="getNameByIdAndCode",query = "SELECT s FROM SPR_EXAM_CODE s where s.id= :examCode and s.typeExp = :codeExp")
})
public class SPR_EXAM_CODE implements Serializable {


    @Column(name="ID")
    private String id;

    @Id
    @Column(name="NAME_EXP")
    private String nameExp;

    @Column(name="TYPE_EXP")
    private String typeExp;

    @OneToOne(optional = false)
    @JoinColumn(name="TYPE_EXP",insertable = false,updatable = false)
    private EXP_NAME expName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameExp() {
        return nameExp;
    }

    public void setNameExp(String nameExp) {
        this.nameExp = nameExp;
    }

    public String getTypeExp() {
        return typeExp;
    }

    public void setTypeExp(String typeExp) {
        this.typeExp = typeExp;
    }

    public EXP_NAME getExpName() {
        return expName;
    }

    public void setExpName(EXP_NAME expName) {
        this.expName = expName;
    }
}
