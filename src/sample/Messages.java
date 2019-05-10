package sample;

public class Messages {
    private int id;
    private String typSpravy;
    private String nazovSpravy;
    private String text;
    private double cena;
    private int prijemca;
    private int odosielatel;


    public Messages(String typSpravy, String nazovSpravy, String text, int prijemca, int odosielatel) {
        this.typSpravy = typSpravy;
        this.nazovSpravy = nazovSpravy;
        this.text = text;
        this.prijemca = 1;
        this.odosielatel = odosielatel;
    }

}
