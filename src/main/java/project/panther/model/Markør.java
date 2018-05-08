package project.panther.model;

import java.time.LocalDateTime;

public class Markør {

    private int markørID;
    private int lattitude;
    private int longtitude;
    private LocalDateTime oprettelsesTidspunkt;
    private LocalDateTime afslutningsTidspunkt;
    private Pant pant;

    public Markør(int markørID, int lattitude, int longtitude, LocalDateTime oprettelsesTidspunkt, LocalDateTime afslutningsTidspunkt, Pant pant) {
        this.markørID = markørID;
        this.lattitude = lattitude;
        this.longtitude = longtitude;
        this.oprettelsesTidspunkt = oprettelsesTidspunkt;
        this.afslutningsTidspunkt = afslutningsTidspunkt;
        this.pant = pant;
    }

    public int getMarkørID() {
        return markørID;
    }

    public void setMarkørID(int markørID) {
        this.markørID = markørID;
    }

    public int getLattitude() {
        return lattitude;
    }

    public void setLattitude(int lattitude) {
        this.lattitude = lattitude;
    }

    public int getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(int longtitude) {
        this.longtitude = longtitude;
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

    public Pant getPant() {
        return pant;
    }

    public void setPant(Pant pant) {
        this.pant = pant;
    }
}
