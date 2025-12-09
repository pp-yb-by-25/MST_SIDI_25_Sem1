package MODULE_1_JAVA.java_tps_object_oriented_programming.java_tp2_inheritance_digital_documents;

import java.io.File;
import java.io.IOException;

public abstract class DigitalDocument implements Document{


    //Attributs privés
    private String title;
    private String author;
    private int year;


    //Constructeurs
    public DigitalDocument(String title,String author , int year)
    {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    //Méthdode finale
    public final String getDocumentInfo()
    {
        return ""+this.title+";"+this.author+";"+this.year+";"+this.getType()+"";
    }
    //Méthode protected 
    protected abstract String getType();
    //Méthodes abstraites
    @Override
    public String getTitle()
    {
        return this.title;
    }
    @Override
    public String getAuthor()
    {
        return this.author;
    }
    @Override
    public int getYear()
    {
        return this.year;
    }
    public void saveToDisk(File directory) throws IOException
    {
        
    }

}
