package Project2.Tp1.Exercice2;

import java.util.Scanner;

public class Temps_mod {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Fait-il beau ?");
        String resp = sc.nextLine().toLowerCase();
        while(!(resp.equals("oui")) && !(resp.equals("non")) && !(resp.equals("o"))  && !(resp.equals("n")) )
        {
            System.out.println("Merci de resaisir votre réponse Oui ou Non");
            resp = sc.next().toLowerCase();
        }
        if(resp.equals("oui") || resp.equals("o"))
        {
            System.out.println("Tant mieux !");
        }
        else
        {
            System.out.println("Esperons mieux demain !");
        }
        sc.close();
    }

}
