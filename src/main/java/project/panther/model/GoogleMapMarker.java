/* Written by: Max Møller Hoffmeyer, Thomas Bo Nielsen & Sidney Schultz */

package project.panther.model;


import java.time.LocalDateTime;

public class GoogleMapMarker {
    private int markerID;
    private int brugerID;
    private double latitude;
    private double longitude;
    private LocalDateTime oprettelsesTidspunkt;
    private LocalDateTime afslutningsTidspunkt;
    private Pant pant;


    public GoogleMapMarker(int markerID, int brugerID, double latitude, double longitude, LocalDateTime oprettelsesTidspunkt, LocalDateTime afslutningsTidspunkt, Pant pant) {
        this.markerID = markerID;
        this.brugerID = brugerID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.oprettelsesTidspunkt = oprettelsesTidspunkt;
        this.afslutningsTidspunkt = afslutningsTidspunkt;
        this.pant = pant;
    }

    public GoogleMapMarker(int brugerID, double latitude, double longitude, LocalDateTime oprettelsesTidspunkt, LocalDateTime afslutningsTidspunkt, Pant pant){
        this.brugerID = brugerID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.oprettelsesTidspunkt = oprettelsesTidspunkt;
        this.afslutningsTidspunkt = afslutningsTidspunkt;
        this.pant = pant;
    }

    public GoogleMapMarker(){}

    public int getMarkerID() {
        return markerID;
    }

    public void setMarkerID(int markerID) {
        this.markerID = markerID;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLattitude(int latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
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

    public Pant getPant() {
        return pant;
    }

    public void setPant(Pant pant) {
        this.pant = pant;
    }
}
