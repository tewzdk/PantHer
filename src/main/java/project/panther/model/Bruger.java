package project.panther.model;

import java.util.ArrayList;

public class Bruger {


    private int brugerID;
    private String mail;
    private String fornavn;
    private String efternavn;
    private String telefonnummer;
    private String kodeord;
    private String profilbilledesti;
    private boolean enabled;
    private String role;
    //her kan vi ændre default-profilbillede (men kun for nye brugere).
    private ArrayList<Adresse> brugeradresser;




    public Bruger(){}

    public Bruger(String mail, String fornavn, String efternavn, String telefonnummer, String kodeord) {
        this.mail = mail;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.telefonnummer = telefonnummer;
        this.kodeord = kodeord;
        this.profilbilledesti = "pictures/avatar.png";
        this.enabled = true;
        this.role = "ROLE_admin";
    }

    public Bruger(int brugerID, String kodeord, String fornavn, String efternavn, String mail, String telefonnummer, String profilbilledesti, boolean enabled, String role) {
        this.brugerID = brugerID;
        this.kodeord = kodeord;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.mail = mail;
        this.telefonnummer = telefonnummer;
        this.profilbilledesti = profilbilledesti;
        this.enabled = enabled;
        this.role = role;
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

    public String getProfilbilledesti() {
        return profilbilledesti;
    }

    public void setProfilbilledesti(String profilbilledesti) {
        this.profilbilledesti = profilbilledesti;
    }

    public ArrayList<Adresse> getBrugeradresser() {
        return brugeradresser;
    }

    public void setBrugeradresser(ArrayList<Adresse> brugeradresser) {
        this.brugeradresser = brugeradresser;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
