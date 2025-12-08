package Project3.Gp4;

public abstract class Véhicule{
    //Attributs
    private String brand;
    private final int id;
    private static int counter=0;
    private int speed;

    //Constructeur
    public Véhicule(String brand,int speed)
    {
        Véhicule.counter++;
        this.brand = brand;
        this.speed = speed;
        this.id = counter;
    }

    //Getters and Setters
    public String getBrand(){return this.brand;}
    public final int getId(){return this.id;}
    public static int getCounter(){return counter;}
    public int getSpeed(){return this.speed;}

    public void setBrand(String brand){this.brand = brand;}
    public void setSpeed(int speed){this.speed = speed;}

    //methodes

    public abstract void honk();
    public void accelerate(){this.speed+=Drivable.DEFAULT_SPEED;}
    public void accelerate(int speed){this.speed+=speed;}

}
