package bank_account;

import java.util.Date;

public class Expenses {
    private int id;
    private String nazov_vkladu;
    private String typ_vydavku;
    private Date datum;
    private double vyska_vydavku;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazov_vkladu() {
        return nazov_vkladu;
    }

    public void setNazov_vkladu(String nazov_vkladu) {
        this.nazov_vkladu = nazov_vkladu;
    }

    public String getTyp_vydavku() {
        return typ_vydavku;
    }

    public void setTyp_vydavku(String typ_vydavku) {
        this.typ_vydavku = typ_vydavku;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getVyska_vydavku() {
        return vyska_vydavku;
    }

    public void setVyska_vydavku(double vyska_vydavku) {
        this.vyska_vydavku = vyska_vydavku;
    }
}
