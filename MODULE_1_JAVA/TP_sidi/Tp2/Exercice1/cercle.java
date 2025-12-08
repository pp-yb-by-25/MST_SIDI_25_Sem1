package Project2.Tp2.Exercice1;

public class cercle extends forme
{
        //Attributs
        private float rayon;

        //Constructeurs par defaut et par arguments
        public cercle(){this.rayon=0;}
        public cercle(float X, float Y,float rayon){this.setX(X);this.setY(Y);this.rayon=rayon;}

        //Setters
        public void setRayon(float rayon){this.rayon = rayon;}
        
        //Getters
        public float getRayon(){return this.rayon;}

        //toString pour l'affichage
        public String toString(){return "Cercle(O(+"+this.getX()+" , "+this.getY()+") , R = "+this.rayon+")";}

        //Methodes abstraites
        @Override
        public float surface(){return (float)(Math.PI*Math.pow(this.rayon,2));}
        @Override
        public float perimetre(){return (float)(2*Math.PI*this.rayon);}
}
