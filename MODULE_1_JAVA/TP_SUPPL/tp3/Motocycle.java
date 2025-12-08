package Project3.Gp4;

public class Motocycle extends Véhicule implements Drivable {
    //Attributs
    private boolean hasHelmet;

    //Constructeur
    public Motocycle(String brand, int speed , boolean hasHelmet)
    {
        super(brand,speed);
        this.hasHelmet = hasHelmet;
    }
    //getters and setters 
    public boolean gethasHelmet(){return this.hasHelmet;}
    public void setHasHelmet(boolean hasHelmet){this.hasHelmet = hasHelmet;}
    //Methodes 
    


    //Methdoes de classe mère 
    public void honk(){System.out.println("BipBip");}
    
    //Methdoe de interface 
    @Override
    public void start(){System.out.println("le Motocycle démarre");}
    @Override
    public void stop(){System.out.println("le Motocycle s'arrête");}
    @Override
    public void move(){System.out.println("Le motocycle s'avance à "+this.getSpeed()+"");}
    
}
