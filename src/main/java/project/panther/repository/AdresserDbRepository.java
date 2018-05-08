package project.panther.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import project.panther.model.Adresse;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdresserDbRepository {

    @Autowired
    JdbcTemplate jdbc;
    SqlRowSet sqlRowSet;


    public List<Adresse> readAll() {
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

    public void create(Adresse adresse) {
        jdbc.update("INSERT INTO PantHer.adresser " +
                "(adresse_id, gade, husnummer, etage, postnummer, bynavnbrugeradresser) " +
                "VALUES ('" + adresse.getGade()
                +"', '"+ adresse.getHusnummer()
                + "', '"+ adresse.getEtage() +"', '"
                + adresse.getPostnummer() +"', '"+ adresse.getBynavn() + "')");
    }

    public Adresse read(int id) {
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

    public void update(Adresse adresse) {
        jdbc.update("UPDATE adresser SET") +
                "gade = '"+ adresse.getGade() + "', " +
                "husnummer = '"+ adresse.getHusnummer() + "', " +
                "etage = '"+ adresse.getEtage() + "', " +
                "postnummer = '"+ adresse.getPostnummer() + "', " +
                "bynavnbrugeradresser = '"+ adresse.getBynavn() +

                "'WHERE adresse_id ='"+ adresse.getAdresseID() + "'");

    }
    public void delete(int id) {

        jdbc.update("DELETE FROM adresser WHERE adresse_id='" + id + "'");

    }

}
