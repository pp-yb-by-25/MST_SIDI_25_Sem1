package Project3.Gp3;

public class Lion extends Animal implements Movable {
    //Attributs 
    public static int age;
    //Constructeur
    public Lion(String name,int age)
    {
        super(name);
        Lion.age=age;
    }
    //Les méthodes abstraites 
    @Override
    public void move()
    {
        System.out.println("le lion se déplace à "+DEFAULT_SPEED+ " km/h");
    }
    @Override 
    public void makeSound()
    {
        System.out.println("Lion rugit");
    }
}
