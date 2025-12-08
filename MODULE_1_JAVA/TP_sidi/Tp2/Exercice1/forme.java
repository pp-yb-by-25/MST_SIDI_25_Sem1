package Project2.Tp2.Exercice1;

public abstract class forme {
    //Attributs
    private float X;
    private float Y;

    //Constructeurs
    public forme(){this.X=0;this.Y=0;}
    public forme(float X,float Y){this.X=X;this.Y=Y;}

    //Destructeur
    public void finalize(){System.out.println("Forme détruit !");}
    //Getters
    public float getX(){return this.X;}
    public float getY(){return this.Y;}

    //Setters
    public void setX(float X){this.X = X;}
    public void setY(float Y){this.Y = Y;}

    //Méthode non abstraite
    public void deplacer(float Xn,float Yn){this.X+=Xn;this.Y+=Yn;}

    //Méthodes abstraites
    public abstract float surface();
    public abstract float perimetre();


}
