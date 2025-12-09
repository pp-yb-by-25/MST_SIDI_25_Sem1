package Project3.Gpt1;

import java.util.Scanner;
import java.util.ArrayList;

public class Tableau
{
    public static void main(String args[])
    {
        ArrayList<Integer> tab = new ArrayList<>();
        
        for (int i =0 ; i<10 ; i++)
        {
            Scanner scTemp = new Scanner(System.in);
            System.out.println("Enter the number  "+i+ " : ");
            tab.add(scTemp.nextInt());
            if(i==10)
            {
                scTemp.close();
            }
        }
        tab.remove(Integer.valueOf(2));
        System.out.println(tab);
    }
}