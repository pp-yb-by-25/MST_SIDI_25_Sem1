package MODULE_1_JAVA.java_tps_object_oriented_programming.java_tp3_interfaces_polymorphism_vehicles;
public interface Loadable {
    //Attributs
    public final int MAX_LOAD = 500;
    //Méthodes abstraites
    public void load(double weight);
    public void unload();
}
