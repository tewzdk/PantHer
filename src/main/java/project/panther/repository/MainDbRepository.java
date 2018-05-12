package project.panther.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import project.panther.model.*;

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
                    sqlRowSet.getString("bynavn")));
        }
        return adresses;
    }



    @Override // SKAL KUNNE LINKE BRUGER OG ADRESSE
    public void createAdresse(Adresse adresse, int brugerId) {
        jdbc.update("INSERT INTO PantHer.adresser " +
                "(adresse_id, gade, husnummer, etage, postnummer, bynavn) " +
                "VALUES ('" +
                adresse.getGade() +"', '"+
                adresse.getHusnummer() + "', '"+
                adresse.getEtage() +"', '" +
                adresse.getPostnummer() +"', '"+
                adresse.getBynavn() + "')"
        );

        createBrugerAdresse(brugerId,adresse.getAdresseID());

    }

    @Override
    public void createBruger(Bruger bruger) {
        jdbc.update("INSERT INTO panther.brugere " +
                "(mail, fornavn, efternavn, telefonnummer, kodeord, profilbillede_sti) " +
                "VALUES ('" +
                bruger.getMail() +"', '"+
                bruger.getFornavn() + "', '"+
                bruger.getEfternavn() + "', '"+
                bruger.getTelefonnummer() + "', '" +
                bruger.getKodeord() + "', '"+
                bruger.getProfilbilledeSti() +"')");

    }

    @Override
    public void createBrugerAdresse(int brugerId, int adresseId) {
        jdbc.update("INSERT INTO PantHer.brugeradresser " +
                "(bruger_id, adresse_id) " +
                "VALUES ('" +
                brugerId + "', '" +
                adresseId   + "')"

        );
    }

    @Override
    public Bruger readbruger(int id) {
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM PantHer WHERE bruger_id ='"+ id + "'");

        while (sqlRowSet.next()) {
            return new Bruger(
                    sqlRowSet.getInt("bruger_id"),
                    sqlRowSet.getString("kodeord"),
                    sqlRowSet.getString("fornavn"),
                    sqlRowSet.getString("efternavn"),
                    sqlRowSet.getString("mail"),
                    sqlRowSet.getString("telefonnummer"),
                    sqlRowSet.getString("profilbillede_sti"));
        }
        return null;
    }




    @Override
    public Adresse readadresse(int id)  {
        sqlRowSet = jdbc.queryForRowSet("SELECT panther.adresser.gade, panther.adresser.husnummer, panther.adresser.etage, panther.adresser.postnummer, panther.adresser.bynavn FROM PantHer.adresser " +
                "INNER JOIN panther.brugeradresser ON adresser.adresse_id = brugeradresser.adresse_id" +
                "INNER JOIN panther.brugere ON brugeradresser.bruger_id = brugere.bruger_id " +
                "WHERE adresse_id ='"+ id + "'");

        while (sqlRowSet.next()) {
            return new Adresse(
                    sqlRowSet.getInt("adresse_id"),
                    sqlRowSet.getString("gade"),
                    sqlRowSet.getString("husnummer"),
                    sqlRowSet.getString("etage"),
                    sqlRowSet.getInt("postnummer"),
                    sqlRowSet.getString("bynavn"));
        }
        return null;
    }

    @Override
    public void updateBruger(Bruger bruger) {
        jdbc.update("UPDATE panther.adresser SET " +
                "kodeord = '" + bruger.getKodeord()+ "', " +
                "fornavn = '"+ bruger.getFornavn() + "', " +
                "efternavn = '"+ bruger.getEfternavn() + "', " +
                "mail = '"+ bruger.getMail() + "', " +
                "telefonnummer = '"+ bruger.getTelefonnummer() + "', " +
                "profilbillede_sti = '"+ bruger.getProfilbilledeSti() +

                "'WHERE adresse_id ='"+ bruger.getBrugerID() + "'");

    }

    @Override
    public void updateAdresse(Adresse adresse) {
        jdbc.update("UPDATE panther.adresser SET " +
                "gade = '" + adresse.getGade() + "', " +
                "husnummer = '"+ adresse.getHusnummer() + "', " +
                "etage = '"+ adresse.getEtage() + "', " +
                "postnummer = '"+ adresse.getPostnummer() + "', " +
                "bynavn = '"+ adresse.getBynavn() +

                "'WHERE adresse_id ='"+ adresse.getAdresseID() + "'");
    }

    @Override
    public void deleteBruger(int id) {
        jdbc.update("DELETE FROM brugere WHERE bruger_id='" + id + "'");

    }

    @Override
    public void deleteAdresse(int id) {
        jdbc.update("DELETE FROM adresser WHERE adresse_id='" + id + "'");
    }



    //-------------------------------- GOOGLE MAP MARKERS --------------------------------//

    @Override
    public List<GoogleMapMarker> readAllGoogleMapMarkers() {
        List<GoogleMapMarker> googleMapMarkerList = new ArrayList<>();
        String sql= "SELECT * FROM PantHer.markører";
        sqlRowSet = jdbc.queryForRowSet(sql);

        while (sqlRowSet.next()) {

            int estimeret_beløb = sqlRowSet.getInt("estimeret_beløb");
            String pantbillede_sti = sqlRowSet.getString("pantbillede_sti");
            Pant pant = new Pant(estimeret_beløb, pantbillede_sti);

            googleMapMarkerList.add(new GoogleMapMarker(
                    sqlRowSet.getInt("markør_id"),
                    sqlRowSet.getInt("bruger_id"),
                    sqlRowSet.getDouble("latitude"),
                    sqlRowSet.getDouble("longitude"),
                    sqlRowSet.getTimestamp("oprettelsestidspunkt").toLocalDateTime(),
                    sqlRowSet.getTimestamp("afslutningstidspunkt").toLocalDateTime(),
                    pant));
        }
        return googleMapMarkerList;
    }
    public GoogleMapMarker readGoogleMapMarker(int id) {
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM panther WHERE markør_id = '" + id + "'");

        while (sqlRowSet.next()) {
            int estimeret_beløb = sqlRowSet.getInt("estimeret_beløb");
            String pantbillede_sti = sqlRowSet.getString("pantbillede_sti");
            Pant pant = new Pant(estimeret_beløb, pantbillede_sti);

            return new GoogleMapMarker(
                    sqlRowSet.getInt("markør_id"),
                    sqlRowSet.getInt("bruger_id"),
                    sqlRowSet.getInt("lattitude"),
                    sqlRowSet.getInt("longtitude"),
                    sqlRowSet.getTimestamp("oprettelsestidspunkt").toLocalDateTime(),
                    sqlRowSet.getTimestamp("afslutningstidspunkt").toLocalDateTime(),
                    pant);
        }
        return null;
    }

    @Override
    public void updateGoogleMapMarker(GoogleMapMarker marker) {
        jdbc.update("UPDATE panther.adresser SET " +
                "lattitude = '" + marker.getLatitude() + "', " +
                "longtitude = '"+ marker.getLongitude() + "', " +
                "oprettelsestidspunkt = '"+ marker.getOprettelsesTidspunkt() + "', " +
                "afslutningsTidspunkt = '"+ marker.getAfslutningsTidspunkt() + "', " +
                "pant = '"+ marker.getPant() +

                "'WHERE adresse_id ='"+ marker.getMarkerID() + "'");

    }

    @Override
    public void deleteGoogleMapMarker(int id) {
        jdbc.update("DELETE FROM markører WHERE markør_id='" + id + "'");
    }
}
