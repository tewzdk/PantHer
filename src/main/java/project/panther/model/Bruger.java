package project.panther.model;

import java.util.ArrayList;

public class Bruger {

    private int brugerID;
    private String kodeord;
    private String fornavn;
    private String efternavn;
    private String mail;
    private String telefonnummer;
    private String profilbilledeSti;
    private ArrayList<Adresse> brugeradresser;

    public Bruger(int brugerID, String kodeord, String fornavn, String efternavn, String mail, String telefonnummer, String profilbilledeSti, ArrayList<Adresse> brugeradresser) {
        this.brugerID = brugerID;
        this.kodeord = kodeord;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.mail = mail;
        this.telefonnummer = telefonnummer;
        this.profilbilledeSti = profilbilledeSti;
        this.brugeradresser = brugeradresser;
    }

    @Override
    public String toString() {
        return "Bruger{" +
                "brugerID=" + brugerID +
                ", kodeord='" + kodeord + '\'' +
                ", fornavn='" + fornavn + '\'' +
                ", efternavn='" + efternavn + '\'' +
                ", mail='" + mail + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                ", profilbilledeSti='" + profilbilledeSti + '\'' +
                ", brugeradresser=" + brugeradresser +
                '}';
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public String getKodeord() {
        return kodeord;
    }

    public void setKodeord(String kodeord) {
        this.kodeord = kodeord;
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

    public ArrayList<Adresse> getBrugeradresser() {
        return brugeradresser;
    }

    public void setBrugeradresser(ArrayList<Adresse> brugeradresser) {
        this.brugeradresser = brugeradresser;
    }
}
