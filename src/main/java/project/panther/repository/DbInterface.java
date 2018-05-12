package project.panther.repository;

import project.panther.model.Adresse;
import project.panther.model.Bruger;
import project.panther.model.GoogleMapMarker;
import project.panther.model.GoogleMapMarkerList;

import java.util.List;

public interface DbInterface {

    //READALL
    List<Bruger> readAllBruger();
    List<Adresse> readAllAdresse();
    List<GoogleMapMarker> readAllGoogleMapMarkers();
    //CREATE
    void createAdresse (Adresse adresse, int brugerId);
    void createBruger (Bruger bruger);
    void createBrugerAdresse (int brugerId, int adresseId);
    //READ
    Bruger readbruger(int id);
    Adresse readadresse(int id);
    GoogleMapMarker readGoogleMapMarker(int id);
    //UPDATE
    void updateBruger(Bruger bruger);
    void updateAdresse (Adresse adresse);
    void updateGoogleMapMarker(GoogleMapMarker marker);
    //DELETE
    void deleteBruger(int id);
    void deleteAdresse (int id);
    void deleteGoogleMapMarker (int id);



}
