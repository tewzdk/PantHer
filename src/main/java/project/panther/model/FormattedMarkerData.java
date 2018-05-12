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
    private String pantbilledeSti;

    //user
    private String fornavn;
    private String efternavn;
    private String telefonnummer;
    private String profilbilledeSti;

    public FormattedMarkerData(
                            double latitude, double longitude,
                            LocalDateTime oprettelsesTidspunkt,
                            LocalDateTime afslutningsTidspunkt,
                            int estimeretBeloeb,
                            String pantbilledeSti,
                            String fornavn,
                            String efternavn,
                            String telefonnummer,
                            String profilbilledeSti
    ) {

        this.latitude = latitude;
        this.longitude = longitude;

        //TODO formatér tidspunkter til strings:
        this.oprettelsesTidspunkt = oprettelsesTidspunkt;
        this.afslutningsTidspunkt = afslutningsTidspunkt;

        this.estimeretBeloeb = estimeretBeloeb;
        this.pantbilledeSti = pantbilledeSti;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
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

    public String getPantbilledeSti() {
        return pantbilledeSti;
    }

    public void setPantbilledeSti(String pantBilledSti) {
        this.pantbilledeSti = pantBilledSti;
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
