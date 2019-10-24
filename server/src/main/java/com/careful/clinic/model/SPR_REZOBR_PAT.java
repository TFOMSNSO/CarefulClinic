package com.careful.clinic.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_SPR_REZOBR")
public class SPR_REZOBR_PAT {
    @Id
    private String code;

    private String name;

    @Override
    public String toString() {
        return "SPR_REZOBR_PAT{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
