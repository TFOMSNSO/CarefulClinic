package com.careful.clinic.model;

public class ResponseDescription {
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
