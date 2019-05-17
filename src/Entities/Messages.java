package Entities;

import javafx.beans.property.SimpleStringProperty;

public class Messages {
    private SimpleStringProperty id;
    private SimpleStringProperty typSpravy;
    private SimpleStringProperty nazovSpravy;
    private SimpleStringProperty obsah;
    private SimpleStringProperty cena;
    private SimpleStringProperty prijemca;
    private SimpleStringProperty odosielatel;
    private SimpleStringProperty odosielatelName;
    private SimpleStringProperty datum;
    private SimpleStringProperty operacia;

    public Messages(String id,String typSpravy, String nazovSpravy, String obsah, String cena, String prijemca, String odosielatel,String odosielatelName, String datum) {
        this.id=new SimpleStringProperty(id);
        this.typSpravy=new SimpleStringProperty(typSpravy);
        this.nazovSpravy=new SimpleStringProperty(nazovSpravy);
        this.obsah =new SimpleStringProperty(obsah);
        this.cena = new SimpleStringProperty(cena);
        this.prijemca = new SimpleStringProperty(prijemca);
        this.odosielatel = new SimpleStringProperty(odosielatel);
        this.odosielatelName=new SimpleStringProperty(odosielatelName);
        this.datum = new SimpleStringProperty(datum);
    }
    public Messages(String operacia){
        this.operacia=new SimpleStringProperty(operacia);
    }

    public String getOperacia() {
        return operacia.get();
    }

    public void setOperacia(String operacia) { this.operacia = new SimpleStringProperty(operacia);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) { this.id = new SimpleStringProperty(id);
    }

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
    public String getOdosielatelName() {
        return odosielatelName.get();
    }


    public void setOdosielatelName(String odosielatelName) {
        this.odosielatelName = new SimpleStringProperty(odosielatelName);
    }
    public String getDatum() {
        return datum.get();
    }

    public void setDatum(String datum) {
        this.datum = new SimpleStringProperty(datum);
    }


}
