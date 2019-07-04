package com.careful.clinic.model;

import java.io.Serializable;

public class ResponseDescription implements Serializable {
    private String desccription;

    public ResponseDescription(){
        desccription = "-";
    }

    public ResponseDescription(String msg){
        this.desccription = msg;
    }

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription;
    }

    @Override
    public String toString() {
        return desccription;
    }
}
