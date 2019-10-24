package com.careful.clinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TOR_HOSPCARD {
    @Id
    private String id;

    private String result;

    @Column(name = "ACCOUNT_N")
    private String accountNumber;

    @Column(name = "ACCOUNT_D")
    private Date accountDate;

    @Override
    public String toString() {
        return "TOR_HOSPCARD{" +
                "id='" + id + '\'' +
                ", result='" + result + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountDate=" + accountDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        switch (result){
            case "1": return "выздоровление";
            case "2": return "улучшение";
            case "3": return "без перемен";
            case "4": return "ухудшение";
            case "5": return "умер";
        }

        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }
}
