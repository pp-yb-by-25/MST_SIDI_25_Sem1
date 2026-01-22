package MODULE_1_JAVA.TP_1_BAHIDA_YOUSSEF_MST_SIDI_TA_2025.src.exercice_1;


import java.io.File;

public class GestionDossier {
    //Attributs
    private File dossier;

    //Constructeur par defaut
    public GestionDossier()
    {
        this.dossier = new File("C:\\");
    }
    // Constructeur par argument
    public GestionDossier(File dossier)
    {
        if(dossier.isDirectory())
        {
            this.dossier = dossier;
            System.out.println("Opération réussite, Chemin : "+this.dossier.getAbsolutePath());
        }
        else
        {
            System.out.println("je veux un dossier pas un fichier");
            this.dossier = new File("C:\\");
            System.out.println("le dossier créé est de chemin : "+this.dossier.getAbsolutePath());
        }            
    }

    //Getter and Setter 
    public File getDossier()
    {
        return this.dossier;
    }
    public void setDossier(File dossier)
    {
        this.dossier = dossier;
    }
    //Méthodes

    public void dir()
    {
        String type;
        for(File f : this.dossier.listFiles())
        {
            if(f.isFile())
            {
                type = "<FICH>";
            }
            else
            {
                type = "<DOSS>";
            }
            System.out.println(f.getName() + "  " + type + "  " +  f.lastModified() + "  " +  f.length() );
        }

    }

}
