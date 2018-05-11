package project.panther.model;


import java.time.LocalDateTime;

public class GoogleMapMarker {

    private int markerID;
    private double latitude;
    private double longtitude;
    private LocalDateTime oprettelsesTidspunkt;
    private LocalDateTime afslutningsTidspunkt;
    private Pant pant;


    public GoogleMapMarker(int markerID, double latitude, double longtitude, LocalDateTime oprettelsesTidspunkt, LocalDateTime afslutningsTidspunkt, Pant pant) {
        this.markerID = markerID;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.oprettelsesTidspunkt = oprettelsesTidspunkt;
        this.afslutningsTidspunkt = afslutningsTidspunkt;
        this.pant = pant;
    }

    public int getMarkerID() {
        return markerID;
    }

    public void setMarkerID(int markerID) {
        this.markerID = markerID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLattitude(int latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
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
