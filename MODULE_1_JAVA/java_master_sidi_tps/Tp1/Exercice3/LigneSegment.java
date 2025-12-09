

package MODULE_1_JAVA.java_master_sidi_tps.Tp1.Exercice3;
public class LigneSegment {
    private Point p1;
    private Point p2;
    public LigneSegment(Point p1,Point p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }
    public double length()
    {
        return this.p1.distanceTo(this.p2);
    }
}
