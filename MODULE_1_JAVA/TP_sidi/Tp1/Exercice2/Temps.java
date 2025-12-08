package Project2.Tp1.Exercice2;

import java.util.Scanner;

public class Temps {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Fait-il beau ?");
        String resp = sc.nextLine();
        if(resp.equalsIgnoreCase("oui"))
        {
            System.out.println("Tant mieux !");
        }
        else if(resp.equalsIgnoreCase("non"))
        {
            System.out.println("Esperons mieux demain");
        }
        else
        {
            System.out.println("Je ne comprends pas");
        }

        sc.close();
    }

}
