package com.careful.clinic.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TOR_PAT {
    @Id
    private String id;

    private String account;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Column(name = "AC_DATE")
    private Date acDate;

    private String rezobr;

    @Column(name = "PR_D_N")
    private String prdn;


    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "REZOBR")
    private SPR_REZOBR_PAT sprRezobr;

    @Override
    public String toString() {
        return "TOR_PAT{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", acDate=" + acDate +
                ", rezobr='" + rezobr + '\'' +
                ", prdn='" + prdn + '\'' +
                ", sprRezobr=" + sprRezobr +
                '}';
    }

    public SPR_REZOBR_PAT getSprRezobr() {
        return sprRezobr;
    }

    public void setSprRezobr(SPR_REZOBR_PAT sprRezobr) {
        this.sprRezobr = sprRezobr;
    }

    public String getPrdn() {
        switch (prdn){
            case "1" : return "состоит";
            case "2" : return "взят";
            case "3" : return "не подлежит диспансерному наблюдению";
            case "4" : return "снят по причине выздоровления";
            case "5" : return "снят по другим причинам";
            default: return "не указано";
        }
    }

    public void setPrdn(String prdn) {
        this.prdn = prdn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getAcDate() {
        return acDate;
    }

    public void setAcDate(Date acDate) {
        this.acDate = acDate;
    }

    public String getRezobr() {
        return rezobr;
    }

    public void setRezobr(String rezobr) {
        this.rezobr = rezobr;
    }
}
