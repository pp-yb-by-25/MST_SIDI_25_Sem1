package MODULE_1_JAVA.java_tps_object_oriented_programming.java_tp3_interfaces_polymorphism_vehicles;
public class VéhiculeUtils {
    public static void showVehiculeDetails(Véhicule v)
    {
        System.out.println("id :"+v.getId()+
                            ", marque: "+v.getBrand()+
                            ",vitesse:"+v.getSpeed()+
                            ", classe réelle du véhicule :"+v.getClass());
    }

    public static void CompareSpeed(Véhicule v1,Véhicule v2)
    {
        if(v1.getSpeed()>v2.getSpeed())
        {
            showVehiculeDetails(v1);
            System.out.println(" est meilleur 0000000000000000000000");
        }
        else if(v1.getSpeed()<v2.getSpeed())
        {
             showVehiculeDetails(v2);
             
            System.out.println(" est meilleur 1111111111111111111111");
        }
        else
        {
            System.out.println("ils ont la meme vitesse");
        }
    }
}
