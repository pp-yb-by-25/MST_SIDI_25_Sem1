package Project3.Gp3;

import java.util.ArrayList;
public class Main {
    public static void main(String args[])
    {
        Animal a1 = new Lion("a1", 4);
        Animal a2 = new Elephant("a2", 198.23);
        Animal a3 = new Lion("a3", 1);

        ArrayList<Animal> lstAnimal = new ArrayList<>();
        lstAnimal.add(0,a2);
        lstAnimal.add(1,a3);
        lstAnimal.add(2,a1);
        for(Animal a:lstAnimal)
        {
            if(a instanceof Lion)
            {
                //Upcasting 
                Lion l = (Lion) a;
                l.move();
                l.makeSound();
                System.out.println(l.getId());
            }
            else if( a instanceof Elephant)
            {
                Elephant e = (Elephant)a;
                e.move();
                e.makeSound();
                System.out.println(e.getId());
            }
        }
    }
}
