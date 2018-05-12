package project.panther.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class FormattedMarkerData {

    //marker
    private double latitude;
    private double longitude;
    private LocalDateTime oprettelsesTidspunkt;
    private LocalDateTime afslutningsTidspunkt;

    //pant
    private int estimeretBeloeb;
    private String pantBilledSti;

    //user
    private String fornavn;
    private String efternavn;
    private String mail;
    private String telefonnummer;
    private String profilbilledeSti;

    public FormattedMarkerData(
                            double latitude, double longitude,
                            LocalDateTime oprettelsesTidspunkt,
                            LocalDateTime afslutningsTidspunkt,
                            int estimeretBeloeb,
                            String pantBilledSti,
                            String fornavn,
                            String efternavn,
                            String mail,
                            String telefonnummer,
                            String profilbilledeSti
    ) {

        this.latitude = latitude;
        this.longitude = longitude;

        //TODO formatér tidspunkter til strings:
        this.oprettelsesTidspunkt = oprettelsesTidspunkt;
        this.afslutningsTidspunkt = afslutningsTidspunkt;

        this.estimeretBeloeb = estimeretBeloeb;
        this.pantBilledSti = pantBilledSti;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.mail = mail;
        this.telefonnummer = telefonnummer;
        this.profilbilledeSti = profilbilledeSti;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getOprettelsesTidspunkt() {
        return oprettelsesTidspunkt;
    }

    public void setOprettelsesTidspunkt(LocalDateTime oprettelsesTidspunkt) {
        this.oprettelsesTidspunkt = oprettelsesTidspunkt;
    }

    public LocalDateTime getAfslutningsTidspunkt() {
        return afslutningsTidspunkt;
    }

    public void setAfslutningsTidspunkt(LocalDateTime afslutningsTidspunkt) {
        this.afslutningsTidspunkt = afslutningsTidspunkt;
    }

    public int getEstimeretBeloeb() {
        return estimeretBeloeb;
    }

    public void setEstimeretBeloeb(int estimeretBeloeb) {
        this.estimeretBeloeb = estimeretBeloeb;
    }

    public String getPantBilledSti() {
        return pantBilledSti;
    }

    public void setPantBilledSti(String pantBilledSti) {
        this.pantBilledSti = pantBilledSti;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
    public String getProfilbilledeSti() {
        return profilbilledeSti;
    }

    public void setProfilbilledeSti(String profilbilledeSti) {
        this.profilbilledeSti = profilbilledeSti;
    }
}
