import Tablas.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<Integer, Persona> persones = Importacio.importPersona();
    static HashMap<Integer, Candidatures> candidatures = Importacio.importCandidatures();
    static HashMap<Integer, ComAutonoma> comAutonomes = Importacio.importComAutonomes();
    static HashMap<Integer, Provincia> provincies = Importacio.importProvincies(comAutonomes);
    static HashMap<Integer, Municipi> municipis = Importacio.importMunicipis(provincies);
    static HashMap<Integer, VotsCandidaturesMunicipis> votsCandidaturesMunicipis = Importacio.importVotsCandMunicipis(candidatures, municipis);
    static HashMap<Integer, VotsCandidaturesProvincies> votsCandidaturesProvincies = Importacio.importVotsCandProvincies(candidatures, provincies);
    static HashMap<Integer, VotsCandidaturesCa> votsCandidaturesCA = Importacio.importVotsCandCA(candidatures, comAutonomes);
    static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opcio;
        do {
            System.out.println("1.Mostrar taula completa");
            System.out.println("0.Acabar");
            System.out.print("Selecciona l'opcio:");
            opcio = scan.nextInt();
            scan.nextLine();
            switch (opcio) {
                case 1:
                    mostrarTaula();
                    break;
            }
        } while (opcio != 0);
    }
    public static void mostrarCodiTaules(){
        System.out.println("-----------------------------------------------");
        System.out.println("COM --> Comunitats Autonomes");
        System.out.println("PRO --> Provincies");
        System.out.println("MUN --> Municipis");
        System.out.println("PER --> Persones");
        System.out.println("CAN --> Candidatures");
        System.out.println("VCC --> Vots Candidatures Comunitat Autonoma");
        System.out.println("VCP --> Vots Candidatures Provincies");
        System.out.println("VCM --> Vots Candidatures Municipis");
        System.out.println("-----------------------------------------------");
    }
    public static void mostrarTaula() {
        String taula;
        do {
            mostrarCodiTaules();
            System.out.print("Quina taula vols mostrar(CAP per sortir):");
            taula = scan.nextLine();
            taula = taula.toUpperCase().substring(0, 3);
            switch (taula) {
                case "PRO":
                    System.out.println(provincies.values());
                    break;
                case "COM":
                    System.out.println(comAutonomes.values());
                    break;
                case "MUN":
                    System.out.println(municipis.values());
                    break;
                case "PER":
                    System.out.println(persones.values());
                    break;
                case "CAN":
                    System.out.println(candidatures.values());
                    break;
                case "VCP":
                    System.out.println(votsCandidaturesProvincies.values());
                    break;
                case "VCC":
                    System.out.println(votsCandidaturesCA.values());
                    break;
                case "VCM":
                    System.out.println(votsCandidaturesMunicipis.values());
                    break;
            }
        } while (!taula.equals("CAP"));
    }
    public static int count(String taula, Connection conn) throws SQLException {
        String query = "SELECT COUNT() FROM " + taula;
        PreparedStatement statement = conn.prepareStatement(query);
        statement.executeUpdate();
        ResultSet resultat= statement.executeQuery(query);
        resultat.next();
        int countComanda = resultat.getInt("COUNT()");
        resultat.close();
        statement.close();
        return countComanda;
    }
}
