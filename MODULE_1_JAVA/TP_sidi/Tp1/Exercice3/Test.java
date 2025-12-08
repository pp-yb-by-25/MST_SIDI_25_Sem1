package Project2.Tp1.Exercice3;


public class Test {
    public static void main(String args[])
    {
         Point p1 = new Point(1.2,3.55);
         Point p2 = new Point(4.2,7.55);
         Point p3 = new Point();
         Point p4 = new Point(4,6);

         LigneSegment ls1 = new LigneSegment(p1,p2);
         LigneSegment ls2 = new LigneSegment(p1,p4);

        Point p5 = p1.translate(1,2);
        System.out.println(p5.toString());
        System.out.println(p3.getX());

        p3.setY(100);
        System.out.println(p3.toString());
    
        System.out.println(ls1.length());
        System.out.println(ls2.length());

    }
}
