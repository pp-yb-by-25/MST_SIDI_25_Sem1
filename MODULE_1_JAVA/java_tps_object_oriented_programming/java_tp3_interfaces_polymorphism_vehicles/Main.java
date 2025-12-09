package Project3.Gp4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        //creationndes objets 
        Véhicule v1 = new Car("Ronault", 120, 4);
        Véhicule v2 = new Truck("Ford", 50, 200);
        Véhicule v3 = new Motocycle("fox", 350, true);

        //Creation d'une liste contenat les objets 
        ArrayList<Véhicule> lstVehicules = new ArrayList<>();
        lstVehicules.add(v1);
        lstVehicules.add(v2);
        lstVehicules.add(v3);
        //Appeler les methdoes pour chaque objet 
       
        //Chrgement du camion
        ((Truck)v2).load(200);

        //Compraision de deux véhicule v1 et v3 
        VéhiculeUtils.CompareSpeed(v1, v3);

        //Afficher les infos des véhicules
        for(Véhicule v:lstVehicules)
        {
            if(v instanceof Drivable)
            {
                ((Drivable)v).start();
                ((Drivable)v).move();
                ((Drivable)v).stop();
            }
            VéhiculeUtils.showVehiculeDetails(v);
        }

        System.out.println("Totale des objets créés est : "+Véhicule.getCounter());
    }
}
