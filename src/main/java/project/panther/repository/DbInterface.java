package project.panther.repository;

import project.panther.model.Adresse;
import project.panther.model.Bruger;
import project.panther.model.Markør;

import java.util.List;

public interface DbInterface {

    //READALL
    List<Bruger> readAllBruger();
    List<Adresse> readAllAdresse();
    List<Markør> readAllMarkør();
    //CREATE
    void createAdresse (Adresse adresse);
    void createBruger (Bruger bruger);
    void createMarkør (Markør markør);
    //READ
    Bruger readbruger(int id);
    Adresse readadresse(int id);
    Markør readMarkør(int id);
    //UPDATE
    void updateBruger(Bruger bruger);
    void updateAdresse (Adresse adresse);
    void updateMarkør (Markør markør);
    //DELETE
    void deleteBruger(int id);
    void deleteAdresse (int id);
    void deleteMarkør (int id);


}
