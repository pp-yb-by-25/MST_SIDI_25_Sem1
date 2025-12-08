package Project3.Gpt1;


public class Etudiant {
    private String name;
    private int age;
    private double note1,note2;

    public Etudiant(String name , int age , double note1,double note2)
    {
        this.name = name;
        this.age = age;
        this.note1 = note1;
        this.note2 = note2;
    }
    public void setName(String name){this.name = name;}
    public void setAge(int age){this.age=age;}
    public void setNote1(double note1){this.note1 = note1;}
    public void setNote2(double note2){this.note2 = note2;}
    
    public String getName(){return this.name;}
    public int getAge(){return this.age;}
    public double getNote1(){return this.note1;}
    public double getNote2(){return this.note2;}

    public double moyenne(){return (this.note1+this.note2)/2;}

    public String toString()
    {
        return "Etudiant :" + this.name + "\nAge : "+this.age+"\nNote1 est : "+this.note1+" \nNote2 est :"+this.note2;
    }


}
