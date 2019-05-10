package sample;

import javafx.beans.property.SimpleStringProperty;

public class Animal {

    private SimpleStringProperty meno;
    private SimpleStringProperty datum_narodenia;
    private SimpleStringProperty stav;
    private SimpleStringProperty zdravotna_karta;
    private SimpleStringProperty  trieda;
    private SimpleStringProperty rad;
    private SimpleStringProperty celad;
    private SimpleStringProperty druh;

    public Animal(String meno, String datum_narodenia, String stav,String zdravotna_karta, String trieda, String rad,String celad,String druh){
        this.meno = new SimpleStringProperty(meno);
        this.datum_narodenia = new SimpleStringProperty(datum_narodenia);
        this.stav = new SimpleStringProperty(stav);
        this.zdravotna_karta = new SimpleStringProperty(zdravotna_karta);
        this.trieda = new SimpleStringProperty(trieda);
        this.rad = new SimpleStringProperty(rad);
        this.celad = new SimpleStringProperty(celad);
        this.druh = new SimpleStringProperty(druh);
    }

    public String getMeno() {
        return meno.get();
    }

    public void setMeno(String meno) {
        this.meno = new SimpleStringProperty(meno);
    }

    public String getDatum_narodenia() { return datum_narodenia.get(); }

    public void setDatum_narodenia(String datum_narodenia) { this.datum_narodenia = new SimpleStringProperty(datum_narodenia); }

    public String getStav() {
        return stav.get();
    }

    public void setStav(String stav) {
        this.stav = new SimpleStringProperty(stav);
    }

    public String getZdravotna_karta() {
        return zdravotna_karta.get();
    }

    public void setZdravotna_karta(String zdravotna_karta) {
        this.zdravotna_karta = new SimpleStringProperty(zdravotna_karta);
    }

    public String getTrieda() {
        return trieda.get();
    }

    public void setTrieda(String trieda) {
        this.trieda = new SimpleStringProperty(trieda);
    }

    public String getRad() {
        return rad.get();
    }

    public void setRad(String rad) {
        this.rad = new SimpleStringProperty(rad);
    }

    public String getCelad() {
        return celad.get();
    }

    public void setCelad(String celad) {
        this.celad = new SimpleStringProperty(celad);
    }

    public String getDruh(){return druh.get();}

    public void setDruh(String druh){ this.druh = new SimpleStringProperty(druh);}
}
