
package MODULE_1_JAVA.tp4Exemple;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EtudiantDAO {
    private Connection connexion;

    public EtudiantDAO() {
        try {
            connexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbgestion?user=root&password=root"
            );
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean ajouter(Etudiant e) {
        String sql = "INSERT INTO etudiant (cne, prenom, nom, genre, dateNaissance, email, pays) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, e.getCne());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getNom());
            ps.setString(4, e.getGenre());
            ps.setDate(5, e.getDateNaissance());
            ps.setString(6, e.getEmail());
            ps.setString(7, e.getPays());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) { ex.printStackTrace(); return false; }
    }

    public boolean supprimer(String cne) {
        String sql = "DELETE FROM etudiant WHERE cne=?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, cne);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) { ex.printStackTrace(); return false; }
    }

    public Etudiant chercher(String cne) {
        String sql = "SELECT * FROM etudiant WHERE cne=?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, cne);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Etudiant(
                        rs.getString("cne"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("genre"),
                        rs.getDate("dateNaissance"),
                        rs.getString("email"),
                        rs.getString("pays")
                    );
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return null;
    }

    public boolean mettreAJour(Etudiant e) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nouveau prénom : "); e.setPrenom(sc.nextLine());
        System.out.print("Nouveau nom : "); e.setNom(sc.nextLine());
        System.out.print("Nouveau genre : "); e.setGenre(sc.nextLine());
        System.out.print("Nouvelle date de naissance (aaaa-mm-jj) : "); e.setDateNaissance(Date.valueOf(sc.nextLine()));
        System.out.print("Nouvel email : "); e.setEmail(sc.nextLine());
        System.out.print("Nouveau pays : "); e.setPays(sc.nextLine());

        String sql = "UPDATE etudiant SET prenom=?, nom=?, genre=?, dateNaissance=?, email=?, pays=? WHERE cne=?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, e.getPrenom());
            ps.setString(2, e.getNom());
            ps.setString(3, e.getGenre());
            ps.setDate(4, e.getDateNaissance());
            ps.setString(5, e.getEmail());
            ps.setString(6, e.getPays());
            ps.setString(7, e.getCne());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); return false; }
    }

    public ArrayList<Etudiant> lister() {
        ArrayList<Etudiant> liste = new ArrayList<>();
        String sql = "SELECT cne FROM etudiant";
        try (Statement stmt = connexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) liste.add(chercher(rs.getString("cne")));
        } catch (SQLException ex) { ex.printStackTrace(); }
        return liste;
    }

    public void afficher(Etudiant e) {
        if (e != null) {
            System.out.println("CNE: " + e.getCne());
            System.out.println("Prénom: " + e.getPrenom());
            System.out.println("Nom: " + e.getNom());
            System.out.println("Genre: " + e.getGenre());
            System.out.println("Date de naissance: " + e.getDateNaissance());
            System.out.println("Email: " + e.getEmail());
            System.out.println("Pays: " + e.getPays());
            System.out.println("--------------------");
        } else { System.out.println("Étudiant introuvable"); }
    }

    public void afficherListe(ArrayList<Etudiant> liste) { for (Etudiant e : liste) afficher(e); }

    public void fermer() { try { if (connexion != null) connexion.close(); } catch (SQLException ex) { ex.printStackTrace(); } }

    public static void main(String[] args) {
        EtudiantDAO dao = new EtudiantDAO();

        Etudiant e1 = new Etudiant("E001", "Alice", "Martin", "F", Date.valueOf("2000-05-15"), "alice@mail.com", "France");
        Etudiant e2 = new Etudiant("E002", "Bob", "Durand", "H", Date.valueOf("1999-11-20"), "bob@mail.com", "France");

        dao.ajouter(e1);
        dao.ajouter(e2);

        dao.afficherListe(dao.lister());

        Etudiant recherche = dao.chercher("E001");
        dao.afficher(recherche);

        dao.mettreAJour(recherche);

        dao.supprimer("E002");

        dao.afficherListe(dao.lister());

        dao.fermer();
    }
}
