package Project3.Gpt2;

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
