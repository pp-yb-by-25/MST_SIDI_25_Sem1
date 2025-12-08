package Project3.Gp4;

public class Truck extends Véhicule implements Drivable,Loadable{

    //Attributs 
    private double currentLoad = 0;

    //Constructeurs
    public Truck(String brand,int speed, double currentLoad)
    {
        super(brand,speed);
        this.currentLoad = currentLoad;
    }

    //Getters and Setters
    public double getCurrentLaod(){return this.currentLoad;}
    public void setCurrentLoad(double currentLoad){this.currentLoad = currentLoad;}


    //Methodes abstraites classe mère
    @Override
    public void honk(){System.out.println("TROOOON");}
    //Méthodes des interfaces 
    @Override
    public void start(){System.out.println("le camion démarre");}
    @Override
    public void stop(){System.out.println("le camion s'arrête");}
    @Override
    public void move(){System.out.println("le camion avance à "+this.getSpeed()+" km/h");}

    @Override
    public void load(double weight)
    {
        if(weight>MAX_LOAD)
        {
            System.out.println("Impossible ! , La masse maximale est dépasssée");
            unload();
        }
        this.currentLoad+=weight;

    }
    @Override
    public void unload(){this.currentLoad = 0;}
}
