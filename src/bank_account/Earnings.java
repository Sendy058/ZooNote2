package bank_account;

import java.util.Date;

public class Earnings {
    private int id;
    private String nazov_prijmu;
    private String typ_prijmu;
    private Date datum;
    private double vyska_prijmu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazov_prijmu() {
        return nazov_prijmu;
    }

    public void setNazov_prijmu(String nazov_prijmu) {
        this.nazov_prijmu = nazov_prijmu;
    }

    public String getTyp_prijmu() {
        return typ_prijmu;
    }

    public void setTyp_prijmu(String typ_prijmu) {
        this.typ_prijmu = typ_prijmu;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getVyska_prijmu() {
        return vyska_prijmu;
    }

    public void setVyska_prijmu(double vyska_prijmu) {
        this.vyska_prijmu = vyska_prijmu;
    }
}
