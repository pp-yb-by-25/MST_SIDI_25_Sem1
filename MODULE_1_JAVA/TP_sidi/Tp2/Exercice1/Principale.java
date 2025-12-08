package Project2.Tp2.Exercice1;
import java.util.ArrayList;

public class Principale {
    public static void main(String [] args)
    {
        //Question1:On ne peut pas instancier une classe abstraite
        forme form1 = null;

        //Creer un objet de la classe cercle
        cercle cercl1 = new cercle((float)5,(float)4,(float)(3.5));

        //Changer le rayon de objet cercl1
        cercl1.setRayon(10);

        //Affichage du centre 
        System.out.println(cercl1.toString());

        //Surface de cercl1
        System.out.println("Surface de cercle1 est :"+cercl1.surface());


        //Creer un objet de la classe rectangle
        rectangle rectangl1 = new rectangle(5,4,7,8);

        //Changer la largeur et la longueur 
        rectangl1.setLargeur(8);
        rectangl1.setLongueur(9);

        //Utiliser la methode deplacer 
        rectangl1.deplacer((float)(2.5),(float)(3.5));

        //Affichage de l'emplacement 
        System.out.println(rectangl1.toString());

        //Affichage de la surface
        System.out.println(rectangl1.surface());
        
        //Afficher la position de rectangle1
        System.out.println(rectangl1.getX() + " " + rectangl1.getY());

        //Tester les affectations des objets
        //cercl1 = rectangl1; //resultat : incompatibles
        //rectangl1 = cercl1; //resultat : types incompatibles
        rectangl1=(rectangle)form1;  //resultat : types incompatibles mais on peut faire Casting down

        form1 = cercl1;   //resultat : acceptable
        form1 = rectangl1; //resultat : types compatibles

        // Creation d'une liste pour contenir 2 rectangles et 2 cerles 
        ArrayList<forme> formList = new ArrayList<>();

        //Creer un autre rectangle et un autre cercle
        rectangle rectangl2 = new rectangle(2,4,19,8);
        cercle cercl2 = new cercle((float)5,(float)4,(float)(3.5));

        //Remplir la liste formList
        formList.add(rectangl1);
        formList.add(rectangl2);
        formList.add(cercl1);
        formList.add(cercl2);

        //Parcourir la liste et afficher la surface de chaque objet
        for(forme f:formList)
        {
            System.out.println(f.surface());
        }

    }
}
