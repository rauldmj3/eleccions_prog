package DAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import Tablas.*;


public class CandidaturesDAODB implements DAODB<Candidatures> {
    String taula = "candidatures";

    // CRUD
    public void create(Candidatures c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (eleccio_id,codi_candidatura,nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulacio_nacional) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getEleccio_id());
        statement.setString(2, c.getCodi_candidatura());
        statement.setString(3, c.getNom_curt());
        statement.setString(4, c.getNom_llarg());
        statement.setString(5, c.getCodi_acumulacio_provincia());
        statement.setString(6, c.getCodi_acumulacio_ca());
        statement.setString(7, c.getCodi_acumulacio_nacional());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    public boolean read(Candidatures c, Connection conn) throws SQLException {
        Candidatures er = read(c.getCandidatura_id(), conn);
        if (er == null) return false;
        c.set(er.getEleccio_id(), er.getCodi_candidatura(), er.getNom_curt(), c.getNom_llarg(), c.getCodi_acumulacio_provincia(), c.getCodi_acumulacio_ca(), c.getCodi_acumulacio_nacional());
        return true;
    }

    public Candidatures read(int id, Connection conn) throws SQLException {
        String query = "SELECT eleccio_id,codi_candidatura,nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulacio_nacional FROM " + taula + " WHERE candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        ResultSet resultat = statement.executeQuery(query);
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        resultat.close();
        statement.close();
        return new Candidatures(id, resultat.getInt("eleccio_id"), resultat.getString("codi_candidatura"), resultat.getString("nom_curt"), resultat.getString("nom_llarg"), resultat.getString("codi_acumulacio_provincia"), resultat.getString("codi_acumulacio_ca"), resultat.getString("codi_acumulacio_nacional"));
    }

    public void update(Candidatures c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET eleccio_id=?,codi_candidatura=?,nom_curt=?,nom_llarg=?,codi_acumulacio_provincia=?,codi_acumulacio_ca=?,codi_acumulacio_nacional=? WHERE candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getEleccio_id());
        statement.setString(2, c.getCodi_candidatura());
        statement.setString(3, c.getNom_curt());
        statement.setString(4, c.getNom_llarg());
        statement.setString(5, c.getCodi_acumulacio_provincia());
        statement.setString(6, c.getCodi_acumulacio_ca());
        statement.setString(7, c.getCodi_acumulacio_nacional());
        statement.setInt(8, c.getCandidatura_id());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    public void delete(Candidatures c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getCandidatura_id());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    // ALTRES DAO
    public boolean exists(Candidatures c, Connection conn) throws SQLException {
        return read(c, conn);
    }

    public int count(Connection conn) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + taula;
        PreparedStatement statement = conn.prepareStatement(query);
        statement.executeUpdate();
        ResultSet resultat = statement.executeQuery(query);
        resultat.next();
        int countComanda = resultat.getInt("COUNT(*)");
        resultat.close();
        statement.close();
        return countComanda;
    }

    public List<Candidatures> all(Connection conn) throws SQLException {
        String query = ("SELECT * FROM " + taula);
        Statement statement = conn.createStatement();
        ResultSet resultat = statement.executeQuery(query);
        List<Candidatures> le = new LinkedList<>();
        Candidatures c;
        while (resultat.next()) {
            c = new Candidatures(resultat.getInt("candidatura_id"), resultat.getInt("eleccio_id"), resultat.getString("codi_candidatura"), resultat.getString("nom_curt"), resultat.getString("nom_llarg"), resultat.getString("codi_acumulacio_provincia"), resultat.getString("codi_acumulacio_ca"), resultat.getString("codi_acumulacio_nacional"));
            le.add(c);
        }
        resultat.close();
        statement.close();
        return le;
    }
}
