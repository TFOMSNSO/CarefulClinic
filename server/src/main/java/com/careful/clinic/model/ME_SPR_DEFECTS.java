package com.careful.clinic.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TOR_ME_SPR_DEFECTS")
@NamedQueries({
        @NamedQuery(name="getDefMain",query = "SELECT d FROM ME_SPR_DEFECTS d WHERE d.id = :id")
})
public class ME_SPR_DEFECTS implements Serializable {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME_DEF")
    private String nameDef;

/*
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "nameDefect")
    private ME_MEDEXP_DEFECT defect;
*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameDef() {
        return nameDef;
    }

    public void setNameDef(String nameDef) {
        this.nameDef = nameDef;
    }

    @Override
    public String toString() {
        return nameDef;
    }
}
