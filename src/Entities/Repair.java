package Entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Repair {

    private SimpleStringProperty nazov;
    private SimpleStringProperty popis;
    private SimpleStringProperty stav;
    private SimpleDoubleProperty cena;

    public Repair(String nazov, String popis, String stav, Double cena){
        this.nazov = new SimpleStringProperty(nazov);
        this.popis = new SimpleStringProperty(popis);
        this.stav = new SimpleStringProperty(stav);
        this.cena = new SimpleDoubleProperty(cena);
    }

    public String getNazov() {
        return nazov.get();
    }

    public void setNazov(String nazov) {
        this.nazov = new SimpleStringProperty(nazov);
    }

    public String getPopis() {
        return popis.get();
    }

    public void setPopis(String popis) {
        this.popis = new SimpleStringProperty(popis);
    }

    public String getStav() {
        return stav.get();
    }

    public void setStav(String stav) {
        this.stav = new SimpleStringProperty(stav);
    }

    public double getCena() {
        return cena.get();
    }

    public void setCena(double cena) {
        this.cena = new SimpleDoubleProperty(cena);
    }
}
