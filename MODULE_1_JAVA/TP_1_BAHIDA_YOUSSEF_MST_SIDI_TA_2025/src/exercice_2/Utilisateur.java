package MODULE_1_JAVA.TP_1_BAHIDA_YOUSSEF_MST_SIDI_TA_2025.src.exercice_2;

//import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utilisateur {

    //Attributs

    private String login;
    private String password;

    //Constructeur par defaut 
    public Utilisateur()
    {
        this.login  = "Constr_Par_defaut";
        this.password = "SIDI_TA";
    }
    //Constructeur par arguments 
    public Utilisateur(String login ,  String password)
    {
        this.login = login;
        this.password = password;
    }
    //Getters 
    public String getLogin()
    {
        return this.login;
    }
    public String getPassword()
    {
        return this.password;
    }
    // Setters 
    public void setLogin(String login)
    {
        this.login = login;
    }
    public void setPassword(String password)
    {
        this.password = password; 
    }

    //Méthodes

    public void enregistrer() throws IOException
    {
        // On doit ajouter l'argumet append:true , pour éviter d'écraser le contenu du fichier user.txt
        FileWriter fw = new FileWriter("D:\\user.txt",true);
        fw.write(this.login + "     " + this.password + "\n"); 
        fw.close();  
    }

    public boolean authentifier() throws FileNotFoundException,IOException
    {
        BufferedReader bf  = new BufferedReader(new FileReader("D:\\user.txt")); 
        String ligne;
        while((ligne = bf.readLine()) != null )
        {
            if(ligne.contains(this.login) && ligne.contains(this.password))
            {
                System.out.println("Authentification est réussite !");
                bf.close();//fermer le buffered et terminer le parcours des lignes
                return true;
            }
        }
        System.out.println("Authentification est échouée !");
        bf.close();
        return false;
    } 

    // Pour Supprimer le fichier user.txt
    // Facultative 
    // public void deleteFile() throws IOException
    // {
    //    File fr = new File("D:\\user.txt");
    //    fr.delete();
    // }


    //Vérification avec Login et Password 
   
}
