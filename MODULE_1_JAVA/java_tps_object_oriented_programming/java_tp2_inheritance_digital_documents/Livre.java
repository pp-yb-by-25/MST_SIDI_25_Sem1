package MODULE_1_JAVA.java_tps_object_oriented_programming.java_tp2_inheritance_digital_documents;

public class Livre extends DigitalDocument {
    //Attributs
    //private int pages;
    //Constructeurs
    public Livre(int pages, String author , String title , int year)
    {
        super(title,author,year);
        //this.pages = pages;
    }



    //Méthodes abstraites 
    @Override
    protected String getType()
    {
        return "";
    }



}
