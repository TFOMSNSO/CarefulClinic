package com.careful.clinic.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ME_SPR_VIEWEXP")
@NamedQueries({
        @NamedQuery(name="getNameById",query = "SELECT e FROM EXP_NAME e WHERE e.id = ?1"),
        @NamedQuery(name="findallnames",query = "SELECT e FROM EXP_NAME e")
})
public class EXP_NAME implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME_VIEW")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EXP_NAME(){}

    @Override
    public String toString() {
        return "EXP_NAME[" + name + "]" ;
    }
}
