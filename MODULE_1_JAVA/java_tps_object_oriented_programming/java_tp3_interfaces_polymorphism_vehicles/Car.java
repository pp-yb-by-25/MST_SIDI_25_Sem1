package Project3.Gp4;

public class Car extends Véhicule implements Drivable{


    //Attributs
    private int doors;

    //Constructeur
    public Car(String brand,int speed,int doors)
    {
        super(brand,speed);
        this.doors = doors;
    }
    //Getters and Setters
    public int getDoors(){return this.doors;}
    public void setDoors(int doors)
    {
        this.doors = doors;
    }

    //Methdes abstraites 
    public void honk()
    {
        System.out.println("Pouet pouet");
    }
    @Override
    public void start(){System.out.println("La voiture démarre !");}
    @Override 
    public void stop(){System.out.println("La voiture s'arrête");}
    @Override
    public void move(){System.out.println("La voiture roule à "+this.getSpeed()+ " km/h");}
    
}
