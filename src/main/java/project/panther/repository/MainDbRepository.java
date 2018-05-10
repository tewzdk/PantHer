package project.panther.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import project.panther.model.Adresse;
import project.panther.model.Bruger;
import project.panther.model.Markør;
import project.panther.model.Pant;

import javax.crypto.EncryptedPrivateKeyInfo;
import java.security.CryptoPrimitive;
import java.time.LocalDateTime;
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
        List<Markør> markør = new ArrayList<>();
        String sql= "SELECT * FROM PantHer.markør";
        sqlRowSet = jdbc.queryForRowSet(sql);
        int estimeret_beløb = sqlRowSet.getInt("estimeret_beløb");
        String pantbillede_sti = sqlRowSet.getString("pantbillede_sti");
        Pant pant = new Pant(estimeret_beløb, pantbillede_sti);

        while (sqlRowSet.next()) {
            markør.add(new Markør(
                    sqlRowSet.getInt("markør_id"),
                    sqlRowSet.getInt("latitude"),
                    sqlRowSet.getInt("longtitude"),
                    sqlRowSet.getTimestamp("oprettelsesTidspunkt").toLocalDateTime(),
                    sqlRowSet.getTimestamp("afslutningsTidspunkt").toLocalDateTime(),
                    pant));
        }
        return markør;
    }

    @Override // SKAL KUNNE LINKE BRUGER OG ADRESSE
    public void createAdresse(Adresse adresse, Bruger bruger) {
        jdbc.update("INSERT INTO PantHer.adresser " +
                "(adresse_id, gade, husnummer, etage, postnummer, bynavnbrugeradresser) " +
                "VALUES ('" +
                adresse.getGade() +"', '"+
                adresse.getHusnummer() + "', '"+
                adresse.getEtage() +"', '" +
                adresse.getPostnummer() +"', '"+
                adresse.getBynavn() + "')"
        );

        jdbc.update("INSERT INTO PantHer.brugeradresser " +
                "(bruger_id, adresse_id) " +
                "VALUES ('" +
                bruger.getBrugerID() + "', '" +
                adresse.getAdresseID()   + "')"

        );
    }

    @Override
    public void createBruger(Bruger bruger) {
        //definerer hvilket default-billede vi tildeler nye brugere.
        String profilbilledeSti = "pictures/avatar.jpg";

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
    public void createMarkør(Markør markør) {
        jdbc.update("INSERT INTO PantHer.markører " +
                "(markør_id, latitude, longitude, oprettelsesTidspunkt, afslutningsTidspunkt, pant) " +
                "VALUES ('" + markør.getLattitude()
                +"', '"+ markør.getLongtitude()
                + "', '"+ markør.getOprettelsesTidspunkt() +"', '"
                + markør.getAfslutningsTidspunkt() +"', '"+ markør.getPant() +"')");

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
        sqlRowSet = jdbc.queryForRowSet("SELECT panther.adresser.gade, panther.adresser.husnummer, panther.adresser.etage, panther.adresser.postnummer, panther.adresser.bynavnbrugeradresser FROM PantHer.adresser " +
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
                    sqlRowSet.getString("bynavnbrugeradresser"));
        }
        return null;
    }

    @Override
    public Markør readMarkør(int id) {
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM panther WHERE markør_id = '" + id + "'");

        while (sqlRowSet.next()) {
            int estimeret_beløb = sqlRowSet.getInt("estimeret_beløb");
            String pantbillede_sti = sqlRowSet.getString("pantbillede_sti");
            Pant pant = new Pant(estimeret_beløb, pantbillede_sti);

            return new Markør(
                    sqlRowSet.getInt("markør_id"),
                    sqlRowSet.getInt("lattitude"),
                    sqlRowSet.getInt("longtitude"),
                    sqlRowSet.getTimestamp("oprettelsestidspunkt").toLocalDateTime(),
                    sqlRowSet.getTimestamp("afslutningstidspunkt").toLocalDateTime(),
                    pant);
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
                "bynavnbrugeradresser = '"+ adresse.getBynavn() +

                "'WHERE adresse_id ='"+ adresse.getAdresseID() + "'");
    }

    @Override
    public void updateMarkør(Markør markør) {
        jdbc.update("UPDATE panther.adresser SET " +
                "lattitude = '" + markør.getLattitude() + "', " +
                "longtitude = '"+ markør.getLongtitude() + "', " +
                "oprettelsestidspunkt = '"+ markør.getOprettelsesTidspunkt() + "', " +
                "afslutningsTidspunkt = '"+ markør.getAfslutningsTidspunkt() + "', " +
                "pant = '"+ markør.getPant() +

                "'WHERE adresse_id ='"+ markør.getMarkørID() + "'");

    }

    @Override
    public void deleteBruger(int id) {
        jdbc.update("DELETE FROM brugere WHERE bruger_id='" + id + "'");

    }

    @Override
    public void deleteAdresse(int id) {
        jdbc.update("DELETE FROM adresser WHERE adresse_id='" + id + "'");
    }

    @Override
    public void deleteMarkør(int id) {
    jdbc.update("DELETE FROM markører WHERE markør_id='" + id + "'");
    }
}
