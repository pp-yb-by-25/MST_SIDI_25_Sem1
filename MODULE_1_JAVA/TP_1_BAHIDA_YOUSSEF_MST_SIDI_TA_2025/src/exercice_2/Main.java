package MODULE_1_JAVA.TP_1_BAHIDA_YOUSSEF_MST_SIDI_TA_2025.src.exercice_2;

import java.io.IOException;

public class Main {
    public static void main(String [] args) throws IOException
    {

        // Créer des utilisateurs aléatoires ********************************************************************
        
            // Utilisateur u4 = new Utilisateur();
            // Utilisateur u5 = new Utilisateur("Youssef_bahida", "Master_sidi_2026_TA");//arg1,arg2);
            // Tester les méthodes
            // Sauvegarde-------------------- 
            // u4.enregistrer();
            // u5.enregistrer();
            // Authentification --------------
            // u4.authentifier();
            // u5.authentifier();

        // Créer les utilisateurs de TP ***********************************************************************

            Utilisateur u1 = new Utilisateur("Loguser1 ","2026");
            Utilisateur u2 = new Utilisateur("Loguser2 ","2027");
            Utilisateur u3 = new Utilisateur("Loguser3 ","2028");

        //Tester les getters -----------------
            u1.getLogin();
            u2.getLogin();
            u3.getLogin();
        //Tester les setters
            u1.setPassword("motpass1");
            u2.setPassword("motpass2");
            u3.setPassword("motpass3");

        //Tester les méthodes ------------------
            //enregistrement 
                u1.enregistrer();
                u2.enregistrer();
                u3.enregistrer();

            //Authentification
            //On va étudier deux cas : réussite et échouée 
            //Pour le cas échouée on va utiliser la méthode void setPassword pour changer le password 
            //Mais le password enregister sur le fichier n'a pas changé
            System.out.println("-----------------------------------------------------");
                    //Cas 1  : réussite
                    u2.authentifier();
                    //Cas 2 : échouée 
                    u3.setPassword("2030");
                    u3.authentifier();
        //************************************************************************************************************

            // Pour ajouter plusieurs utilisateurs 
                // for(int i = 0 ; i < 1000 ; i++)
                // {
                //    Utilisateur tempUser = new Utilisateur("Loguser"+i , "motpass"+i );
                //    tempUser.enregistrer();
                // }
        
    }
}
