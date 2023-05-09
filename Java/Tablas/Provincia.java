package Tablas;

public class Provincia {
    int provincia_id;
    ComAutonoma comAutonoma;
    String nom;
    String codi_ine;
    int num_escons;

    public Provincia(int provincia_id, ComAutonoma comAutonoma, String nom, String codi_ine, int num_escons) {
        this.provincia_id = provincia_id;
        this.comAutonoma = comAutonoma;
        this.nom = nom;
        this.codi_ine = codi_ine;
        this.num_escons = num_escons;
    }

    public int getProvincia_id() {
        return provincia_id;
    }

    public ComAutonoma getComAutonoma() {
        return comAutonoma;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi_ine() {
        return codi_ine;
    }

    public int getNum_escons() {
        return num_escons;
    }

    @Override
    public String toString() {
        return "\nProvincia:" +
                "\n\tID: " + provincia_id +
                "\n\tComunitat Autonoma: " + comAutonoma.getNom() +
                "\n\tNom: " + nom +
                "\n\tCodi INE: " + codi_ine +
                "\n\tNumero d'escons: " + num_escons;
    }
}
