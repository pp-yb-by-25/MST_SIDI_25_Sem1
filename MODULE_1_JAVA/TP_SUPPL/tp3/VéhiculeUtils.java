package Project3.Gp4;

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
