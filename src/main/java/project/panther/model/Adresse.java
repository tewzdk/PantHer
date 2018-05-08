package project.panther.model;

public class Adresse {

    private int adresseID;
    private String gade;
    private String husnummer;
    private String etage;
    private int postnummer;
    private String bynavn;

    public int getAdresseID() {
        return adresseID;
    }

    public Adresse(int adresseID, String gade, String husnummer, String etage, int postnummer, String bynavn) {
        this.adresseID = adresseID;
        this.gade = gade;
        this.husnummer = husnummer;
        this.etage = etage;
        this.postnummer = postnummer;
        this.bynavn = bynavn;
    }

    public void setAdresseID(int adresseID) {
        this.adresseID = adresseID;
    }

    public String getGade() {
        return gade;
    }

    public void setGade(String gade) {
        this.gade = gade;
    }

    public String getHusnummer() {
        return husnummer;
    }

    public void setHusnummer(String husnummer) {
        this.husnummer = husnummer;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public String getBynavn() {
        return bynavn;
    }

    public void setBynavn(String bynavn) {
        this.bynavn = bynavn;
    }
}
