package project.panther.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import project.panther.model.Adresse;
import project.panther.model.Bruger;
import project.panther.model.Markør;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MainDbRepository implements DbInterface {

    @Autowired
    JdbcTemplate jdbc;
    SqlRowSet sqlRowSet;

    @Override
    public List<Bruger> readAllBruger() {
        List<Bruger> brugere = new ArrayList<>();
        String sql = "SELECT * FROM  brugere";
        sqlRowSet = jdbc.queryForRowSet(sql);

        while (sqlRowSet.next()) {
            brugere.add(new Bruger(
                    sqlRowSet.getInt("bruger_id"),
                    sqlRowSet.getString("kodeord"),
                    sqlRowSet.getString("fornavn"),
                    sqlRowSet.getString("efternavn"),
                    sqlRowSet.getString("mail"),
                    sqlRowSet.getString("telefonnummer"),
                    sqlRowSet.getString("profilbilledeSti")));
        }
        return  brugere;
    }

    @Override
    public List<Adresse> readAllAdresse() {
        List<Adresse> adresses = new ArrayList<>();
        String sql= "SELECT * FROM PantHer.adresser";
        sqlRowSet = jdbc.queryForRowSet(sql);

        while (sqlRowSet.next()) {
            adresses.add(new Adresse(
                    sqlRowSet.getInt("adresse_id"),
                    sqlRowSet.getString("gade"),
                    sqlRowSet.getString("husnummer"),
                    sqlRowSet.getString("etage"),
                    sqlRowSet.getInt("postnummer"),
                    sqlRowSet.getString("bynavnbrugeradresser")));
        }
        return adresses;
    }

    @Override
    public List<Markør> readAllMarkør() {
        return null;
    }

    @Override
    public void createAdresse(Adresse adresse) {
        jdbc.update("INSERT INTO PantHer.adresser " +
                "(adresse_id, gade, husnummer, etage, postnummer, bynavnbrugeradresser) " +
                "VALUES ('" + adresse.getGade()
                +"', '"+ adresse.getHusnummer()
                + "', '"+ adresse.getEtage() +"', '"
                + adresse.getPostnummer() +"', '"+ adresse.getBynavn() + "')");
    }

    @Override
    public void createBruger(Bruger bruger) {

    }

    @Override
    public void createMarkør(Markør markør) {

    }

    @Override
    public Bruger readbruger(int id) {
        return null;
    }

    @Override
    public Adresse readadresse(int id) {
        List<Adresse> students = new ArrayList<>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM PantHer WHERE adresse_id ='"+ id + "'");

        while (sqlRowSet.next()) {
            return new Adresse(
                    sqlRowSet.getInt("adresse_id"),
                    sqlRowSet.getString("gade"),
                    sqlRowSet.getString("husnummer"),
                    sqlRowSet.getString("etage"),
                    sqlRowSet.getInt("postnummer"),
                    sqlRowSet.getString("bynavnbrugeradresser"));
        }
        return null;
    }

    @Override
    public Markør readMarkør(int id) {
        return null;
    }

    @Override
    public void updateBruger(Bruger bruger) {

    }

    @Override
    public void updateAdresse(Adresse adresse) {
        jdbc.update("UPDATE panther.adresser SET " +
                "gade = '" + adresse.getGade() + "', " +
                "husnummer = '"+ adresse.getHusnummer() + "', " +
                "etage = '"+ adresse.getEtage() + "', " +
                "postnummer = '"+ adresse.getPostnummer() + "', " +
                "bynavnbrugeradresser = '"+ adresse.getBynavn() +

                "'WHERE adresse_id ='"+ adresse.getAdresseID() + "'");
    }

    @Override
    public void updateMarkør(Markør markør) {

    }

    @Override
    public void deleteBruger(int id) {

    }

    @Override
    public void deleteAdresse(int id) {
        jdbc.update("DELETE FROM adresser WHERE adresse_id='" + id + "'");
    }

    @Override
    public void deleteMarkør(int id) {

    }
}
