package Project2.Tp3.Exercice1;

public class Principale {
    public static void main(String args[])
    {
        GestionFichier gf = new GestionFichier("C:\\Users\\parco\\Documents");

        //Tester les getters et les setters 
        System.out.println(gf.getAdresse());
        gf.setAdresse(null);
        System.out.println(gf.getAdresse());
        gf.setAdresse("C:\\Users\\parco\\Documents");

        //Tester la méthode creer un fichier 
        gf.creerFichier("testFocntion.txt");
        gf.creerDossier("DossierTestFocntion");

        //On va creer un fichier à supprimer 
        gf.creerFichier("testFocntion_2.java");
        gf.supprimer("C:\\\\Users\\\\parco\\\\Documents\\testFocntion_2.java");

        //la methode pour lister les fichiers et les dossiers 

        gf.lst();



    }
}
