/* Written by: Michael Hansen & Sidney Schultz */

package project.panther.model;

//TODO mangler at blive fuldt inkorporeret i vores system.
public class Adresse {

    private int adresseID;
    private String gade;
    private String husnummer;
    private String etage;
    private int postnummer;
    private String bynavn;

    public Adresse() {
    }

    public Adresse(String gade, String husnummer, String etage, int postnummer, String bynavn) {
        this.gade = gade;
        this.husnummer = husnummer;
        this.etage = etage;
        this.postnummer = postnummer;
        this.bynavn = bynavn;
    }

    public int getAdresseID() {
        return adresseID;
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
