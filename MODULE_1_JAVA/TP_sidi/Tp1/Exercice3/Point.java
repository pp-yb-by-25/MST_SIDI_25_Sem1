package Project2.Tp1.Exercice3;

public class Point {
    private double x;
    private double y;

    public Point()
    {
        this.x = 0;
        this.y = 0;
    }
    public Point(double x , double y)
    {
        this.x = x;
        this.y = y;
    }
    public double getX()
    {
        return this.x;
    }
    public double getY()
    {
        return this.x;
    }
    public void setX(double x)
    {
    this.x = x;
    }
    public void setY(double y)
    {
    this.y = y;
    }
    public double distanceTo(Point p)
    {
        return Math.sqrt(Math.pow(p.x-this.x,2) + Math.pow(p.y - this.y,2));
    }
    public Point translate(double dx,double dy)
    {
        return new Point(dx+this.x,dy+this.y);
    }

    public String toString()
    {
        return "Point("+this.x+" ," + this.y+")";
    
    }
}
