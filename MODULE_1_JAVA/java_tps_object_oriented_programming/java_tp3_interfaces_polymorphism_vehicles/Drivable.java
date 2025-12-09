package MODULE_1_JAVA.java-tps-object-oriented-programming.java-tp3-interfaces-polymorphism-vehicles;

public interface Drivable
{
    //Constante
    public final int DEFAULT_SPEED = 20;
    //Méthode implicitement abstraites
    public void start();
    public void stop();
    public void move();
}