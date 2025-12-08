package Project3.Gp3;

public abstract class Animal {
    //Attributs
    protected String name;
    protected final int id;
    protected static int counter=0;

    //Constructeur
    public Animal(String name)
    {
        this.name = name;
        //this.id = counter;
        //counter++;
        Animal.counter++;
        this.id = counter;
    }

    //Methodes abstarites 
    public abstract void makeSound();
    //Methodes statiques
    public static int getCounter()
    {
        return Animal.counter;
    }
    //Methdoes finales
    public final int getId()
    {
        return this.id;
    }
    //Methodes normales
    public String getName()
    {
        return this.name;
    }

}
