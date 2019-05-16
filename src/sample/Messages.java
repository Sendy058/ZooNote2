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
    public Messages(){}

    public String getTypSpravy() {
        return typSpravy.get();
    }

    public void setTypSpravy(String typSpravy) { this.typSpravy = new SimpleStringProperty(typSpravy);
    }
    public String getNazovSpravy() {
        return nazovSpravy.get();
    }

    public void setNazovSpravy(String nazovSpravy) { this.nazovSpravy= new SimpleStringProperty(nazovSpravy);
    }

    public String getObsah () { return obsah .get();
    }

    public void setObsah (String obsah ) { this.obsah  = new SimpleStringProperty(obsah );
    }

    public String getCena() { return cena.get();
    }

    public void setCena(String cena) { this.cena = new SimpleStringProperty(cena);
    }

    public String getPrijemca() {
        return prijemca.get();
    }

    public void setPrijemca(String prijemca) {
        this.prijemca = new SimpleStringProperty(prijemca);
    }

    public String getOdosielatel() {
        return odosielatel.get();
    }

    public void setOdosielatel(String odosielatel) {
        this.odosielatel = new SimpleStringProperty(odosielatel);
    }
    public String getDatum() {
        return datum.get();
    }

    public void setDatum(String datum) {
        this.datum = new SimpleStringProperty(datum);
    }


}
