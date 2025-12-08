package Project3.Gp3;

public class Elephant extends Animal implements Movable {
    //Attributs
    private double taille;

    //Constructeur
    public Elephant(String name , double taille)
    {
        super(name);
        this.taille=taille;
    }
    //getters/setters
    public double getTaille()
    {
        return this.taille;
    }
    public void setTaille(double taille)
    {
        this.taille=taille;
    }

    //Methodes abstraites
    public void move()
    {
        System.out.println("Éléphant barrit");
    }
    public void makeSound()
    {
        System.out.println("L'éléphant barrit ");
    }
}
