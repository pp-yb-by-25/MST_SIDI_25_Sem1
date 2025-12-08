package Project2.Tp2.Exercice1;

public class rectangle extends forme {
        //Attributs avec l'accessibilté mode privé
        private float largeur;
        private float longueur;

        //Constructeurs par defaut et par arguments
        public rectangle(){this.largeur=0;this.longueur=0;}
        public rectangle(float X,float Y,float largeur,float longueur){super(X,Y);this.largeur=largeur;this.longueur=longueur;}

        //Getters
        public float getLargeur(){return this.largeur;}
        public float getLongueur(){return this.longueur;}

        //Setters
        public void setLargeur(float largeur){this.largeur=largeur;}
        public void setLongueur(float longueur){this.longueur=longueur;}

        //ToString pour affichage 
        public String toString(){return "Rectangle(Largeur = "+this.largeur+" ; Longueur = "+this.longueur;}

        //Corps des méthodes abstraites
        @Override
        public float surface(){return this.largeur*this.longueur;}
        @Override
        public float perimetre(){return this.largeur*2+this.largeur*2;}
}
