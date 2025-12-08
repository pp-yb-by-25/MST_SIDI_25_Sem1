package Project3.Gpt1;
//import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class boucle {
    public static void main(String args[])
    {
        //Scanner sc1 = new Scanner(System.in);
        //System.out.println("Enter a number !");
        //int N  = sc1.nextInt();
        //int sum = 0;
        //for(int i=0 ; i <= N ; i++)
        // {
        //    sum+=i;
        //}
        // System.out.println(sum);
        // sc1.close();
            
        Etudiant[] etds = {new Etudiant("youssef", 19, 13, 16),
                            new Etudiant("khalid", 27, 20, 16),
                            new Etudiant("mohmed", 21, 18, 16),        
                            };
        ArrayList<Etudiant> etdlst = new ArrayList<>(Arrays.asList(etds));

        for(Etudiant e:etdlst)
        {
            System.out.println(e.moyenne());
            System.out.println(e.toString());
        }
    }
}
