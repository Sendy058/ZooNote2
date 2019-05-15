package sample;

import javafx.beans.property.SimpleStringProperty;

public class Messages {
    private SimpleStringProperty typSpravy;
    private SimpleStringProperty nazovSpravy;
    private SimpleStringProperty obsah;
    private SimpleStringProperty cena;
    private SimpleStringProperty prijemca;
    private SimpleStringProperty odosielatel;
    private SimpleStringProperty datum;

    public Messages(String typSpravy, String nazovSpravy, String obsah, String cena, String prijemca, String odosielatel, String datum) {
        this.typSpravy=new SimpleStringProperty(typSpravy);
        this.nazovSpravy=new SimpleStringProperty(nazovSpravy);
        this.obsah =new SimpleStringProperty(obsah);
        this.cena = new SimpleStringProperty(cena);
        this.prijemca = new SimpleStringProperty(prijemca);
        this.odosielatel = new SimpleStringProperty(odosielatel);
        this.datum = new SimpleStringProperty(datum);
    }

    public String getTypSpravy() {
        return typSpravy.get();
    }

    public SimpleStringProperty typSpravyProperty() {
        return typSpravy;
    }

    public String getNazovSpravy() {
        return nazovSpravy.get();
    }

    public SimpleStringProperty nazovSpravyProperty() {
        return nazovSpravy;
    }

    public String getObsah() {
        return obsah.get();
    }

    public SimpleStringProperty obsahProperty() {
        return obsah;
    }

    public String getCena() {
        return cena.get();
    }

    public SimpleStringProperty cenaProperty() {
        return cena;
    }

    public String getPrijemca() {
        return prijemca.get();
    }

    public SimpleStringProperty prijemcaProperty() {
        return prijemca;
    }

    public String getOdosielatel() {
        return odosielatel.get();
    }

    public SimpleStringProperty odosielatelProperty() {
        return odosielatel;
    }

    public String getDatum() {
        return datum.get();
    }

    public SimpleStringProperty datumProperty() {
        return datum;
    }

    public void setTypSpravy(String typSpravy) {
        this.typSpravy.set(typSpravy);
    }

    public void setNazovSpravy(String nazovSpravy) {
        this.nazovSpravy.set(nazovSpravy);
    }

    public void setObsah(String obsah) {
        this.obsah.set(obsah);
    }

    public void setCena(String cena) {
        this.cena.set(cena);
    }

    public void setPrijemca(String prijemca) {
        this.prijemca.set(prijemca);
    }

    public void setOdosielatel(String odosielatel) {
        this.odosielatel.set(odosielatel);
    }

    public void setDatum(String datum) {
        this.datum.set(datum);
    }
}
