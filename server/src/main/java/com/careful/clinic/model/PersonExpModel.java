package com.careful.clinic.model;

public class PersonExpModel {

    public PersonExpModel(String surname, String firstname, String lastname, String bithday,String dateBegin,String dateEnd){
        this.surname = surname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bithday = bithday;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public PersonExpModel(){}

    private String surname;
    private String firstname;
    private String lastname;
    private String bithday;
    private String dateBegin;
    private String dateEnd;


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBithday() {
        return bithday;
    }

    public void setBithday(String bithday) {
        this.bithday = bithday;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        PersonExpModel other = (PersonExpModel) obj;
        if (bithday == null) {
            if (other.bithday != null)
                return false;
        } else if (!bithday.equals(other.bithday))
            return false;
        if (firstname == null) {
            if (other.firstname != null)
                return false;
        } else if (!firstname.equals(other.firstname))
            return false;
        if (lastname == null) {
            if (other.lastname != null)
                return false;
        } else if (!lastname.equals(other.lastname))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bithday == null) ? 0 : bithday.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "expmodel[" + surname+" , " + firstname + " , " + lastname + " , " + bithday + " , " + dateBegin + " , " + dateEnd + "]";
    }
}
