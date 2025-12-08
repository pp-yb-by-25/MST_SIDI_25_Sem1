package Project2.Tp2.Exercice2;

import java.time.LocalDate;
import java.time.Period;

public class Etudiant implements Personne {
    //Attributs
    private int numSom;
    private String Nom;
    private String Prenom;
    private LocalDate date_naiss;

    //Constructeurs
    public Etudiant(){this.numSom=0;this.Nom="";this.Prenom="";this.date_naiss=LocalDate.of(2000,01,01);}
    public Etudiant(int numSom,String Nom,String Prenom,LocalDate date_naiss)
    {
        this.numSom = numSom;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.date_naiss = date_naiss;
    }

    //Getters and Setters
    public void setNumSom(int numSom){this.numSom = numSom;}
    public void setNom(String Nom){this.Nom = Nom;}
    public void setPrenom(String Prenom){this.Prenom = Prenom;}
    public void setDateNaiss(LocalDate date_naiss){this.date_naiss = date_naiss;}

    public int getNumSom(){return this.numSom;}
    public String getNom(){return this.Nom;}
    public String getPrenom(){return this.Prenom;}
    public LocalDate getDate(){return this.date_naiss;}


    //Methodes abstraite de l'interface
    @Override 
    public int calculerAge()
    {
        LocalDate dateActuelle = LocalDate.now();
        return Period.between(this.date_naiss,dateActuelle).getYears();
    }
    @Override
    public boolean esFrerede(Personne e)
    {   
        //Casting always 
        Etudiant p = (Etudiant)e;
        if(p.getNom().equalsIgnoreCase(this.Nom)){return true;}
        return false;
    }
}
