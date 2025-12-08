package Project2.Tp3.Exercice1;

import java.io.File;
import java.io.IOException;

public class GestionFichier {
    //Attributs de la classe
    private String adresse;
    //Conctructeur par defaut
    public GestionFichier()
    {
        this.adresse = "C:\\";
    }
    //Constructeur par arguments
    public GestionFichier(String adresse)
    {
        this.adresse = adresse;
    }
    //Getters
    public String getAdresse(){return this.adresse;}
    //Setters
    public void setAdresse(String adresse){this.adresse = adresse;}

    //Methodes
    public  void creerFichier(String nomFichier)
    {
        File f = new File(this.adresse);
        if(f.isFile())
        {
            System.out.println("Impossible !");
        }
        else

        {
            try {
                (new File(this.adresse+"\\"+nomFichier)).createNewFile();
                System.out.println("Création fichier réussite");
            } catch (IOException e) {
                e.printStackTrace();
            } 

        }

    }
    public void creerDossier(String nomDossier)
    {
        if((new File(this.adresse)).isDirectory())
        {
            (new File(this.adresse+"\\"+nomDossier)).mkdir();
            System.out.println("Création Dossier réussite");
        }
        else{System.out.println("Impossible de creer le dossier ");}
    }
    public void supprimer(String nomFD)
    {
            File fich_delete = new File(nomFD);
            String path = nomFD;
           
            if(fich_delete.exists())
            {
                /*
                while(!(fich_delete.getParent().equalsIgnoreCase("C:")))
                {
                    path = fich_delete.getParent() + "\\" + path;
                }
                (new File(path)).delete();
                */
                fich_delete.delete();
                System.out.println("Suppression réussite de fichier :"+path);
            }
    }
    public void lst()
    {
        File f  = new File(this.adresse);
        if(f.isDirectory())
        {
            String[] listFilesAndDirec = f.list();
            for(String s:listFilesAndDirec)
            {
                File sf = new File(s) ; 
                System.out.println(sf.getName()+"    "+ getType(sf)+"   "+ sf.lastModified() +"  "+sf.length());
            }
        }
        else {System.out.println("Impossible de lister les dossiers et les fichiers !");} }
        String getType(File fs)
        {
            {
                if(fs.isFile())
                {
                return "<FICH>";
            }
            else if(fs.isDirectory())
            {
                return "<DOSS>";
            }
            else{
                return "Impossible de trouver le Type";
            }
        } 

    }
    public String[] lst_2()
    {
        File f = new File(this.adresse);
        if(f.isDirectory())
        {
            return f.list();
        }
        else
        {
            System.out.println("Impossible de lister les fichiers et dossiers");
        }
        return null;
    }
    // je dois creer une methode qui retourne le type d'une adresse donnée 
    
}
